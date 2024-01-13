package ir.msob.jima.core.beans.nativesupport;

import com.github.fge.jackson.jsonpointer.JsonPointerMessages;
import com.github.fge.jsonpatch.JsonPatchMessages;
import com.github.fge.msgsimple.load.MessageBundleLoader;
import ir.msob.jima.core.commons.annotation.async.CallbackError;
import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.model.BaseModel;
import ir.msob.jima.core.commons.util.NativeUtil;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.TreeSet;

import static ir.msob.jima.core.commons.Constants.CORE_FRAMEWORK_PACKAGE_PREFIX;

/**
 * Provides runtime hints for native image generation.
 */
public class CoreCommonsRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register resource bundle for AspectJ Weaver
        hints.resources().registerResourceBundle("org.aspectj.weaver.weaver-messages");

        // Register annotated types for reflection
        hints.reflection().registerType(CallbackError.class);
        hints.reflection().registerType(MethodStats.class);

        // Register BaseModel and its subclasses for reflection
        NativeUtil.registerBySupper(hints, CORE_FRAMEWORK_PACKAGE_PREFIX, BaseModel.class);

        // Register TreeSet for reflection
        NativeUtil.register(hints, TreeSet.class);

        // Register JSON Patch related classes for reflection
        NativeUtil.register(hints, MessageBundleLoader.class);
        NativeUtil.register(hints, JsonPatchMessages.class);
        NativeUtil.register(hints, JsonPointerMessages.class);
    }
}
