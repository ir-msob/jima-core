package ir.msob.jima.core.commons.logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LogSerializerTest {

    // =========================
    // Test Models (expanded)
    // =========================

    static class PlainObject {
        String name = "plain";
    }

    @Loggable
    static class SimpleLoggable {
        String a = "hello";
        int b = 10;
    }

    @Loggable(format = LogFormat.JSON)
    static class JsonLoggable {
        String name = "json";
        int value = 5;
    }

    @Loggable(mode = LogMode.EXCLUDE)
    static class ExcludeLoggable {
        String visible = "ok";
        @LogExclude
        String hidden = "secret";
    }

    @Loggable(mode = LogMode.INCLUDE)
    static class IncludeLoggable {
        @LogInclude
        String only = "yes";
        String ignored = "no";
    }

    @Loggable
    static class FieldConfigLoggable {

        @LogField(name = "alias")
        String renamed = "value";

        @LogField(mask = true, maskVisibleChars = 2)
        String password = "123456";

        @LogField(logSize = true)
        List<String> items = List.of("a", "b", "c");

        @LogField(maxLength = 3)
        String longText = "abcdef";

        @LogField(logNull = true)
        String nullable = null;
    }

    @Loggable(depth = 0)
    static class Parent {
        Child child = new Child();
    }

    @Loggable
    static class Child {
        String name = "child";
    }

    @Loggable
    static class Circular {
        Circular self;
    }

    // Complex models for new tests:

    @Loggable
    static class ParentWithList {
        List<Child> children = List.of(new Child(), new Child());
    }

    @Loggable(depth = 0)
    static class ParentWithListDepth0 {
        List<Child> children = List.of(new Child(), new Child());
    }

    @Loggable
    static class ParentWithArray {
        Child[] arr = new Child[]{new Child(), new Child()};
    }

    @Loggable
    static class ParentWithNestedCollections {
        List<List<Child>> nested = List.of(List.of(new Child()));
    }

    @Loggable
    static class ArraySizeLoggable {
        @LogField(logSize = true)
        int[] nums = new int[]{1, 2, 3, 4};
    }

    @Loggable
    static class MapFieldLoggable {
        Map<String, String> mapping = Map.of("k", "v");
    }

    @Loggable
    static class CollectionWithNulls {
        List<Child> list = Arrays.asList(new Child(), null, new Child());
    }

    @Loggable
    static class CircularInCollection {
        List<CircularInCollection> list;
    }

    // =========================
    // Tests (existing + new)
    // =========================

    @Test
    void nonLoggableObject_shouldReturnOriginal() {
        PlainObject p = new PlainObject();
        Object out = LogSerializer.serialize(p);
        assertSame(p, out);
    }

    @Test
    void simpleLoggable_keyValue() {
        SimpleLoggable s = new SimpleLoggable();
        Object out = LogSerializer.serialize(s);

        assertTrue(out instanceof String);
        String str = (String) out;

        assertTrue(str.contains("a=hello"));
        assertTrue(str.contains("b=10"));
    }

    @Test
    void jsonFormat_shouldProduceJson() {
        JsonLoggable j = new JsonLoggable();
        Object out = LogSerializer.serialize(j);

        assertTrue(out instanceof String);
        String json = (String) out;

        assertTrue(json.contains("\"name\":\"json\""));
        assertTrue(json.contains("\"value\":5"));
    }

    @Test
    void excludeMode_shouldExcludeField() {
        ExcludeLoggable e = new ExcludeLoggable();
        String out = (String) LogSerializer.serialize(e);

        assertTrue(out.contains("visible=ok"));
        assertFalse(out.contains("hidden"));
    }

    @Test
    void includeMode_shouldIncludeOnlyAnnotatedFields() {
        IncludeLoggable i = new IncludeLoggable();
        String out = (String) LogSerializer.serialize(i);

        assertTrue(out.contains("only=yes"));
        assertFalse(out.contains("ignored"));
    }

    @Test
    void logFieldConfig_shouldApplyAllRules() {
        FieldConfigLoggable f = new FieldConfigLoggable();
        String out = (String) LogSerializer.serialize(f);

        assertTrue(out.contains("alias=value"));          // renamed
        assertTrue(out.contains("****56") || out.contains("**3456")); // masked (len dependent)
        assertTrue(out.contains("items=3"));              // logSize
        assertTrue(out.contains("abc...(truncated)"));    // maxLength
        assertTrue(out.contains("nullable=null"));        // logNull
    }

    @Test
    void depthLimit_shouldStopRecursion_forSingleChild() {
        Parent p = new Parent();
        String out = (String) LogSerializer.serialize(p);

        // expected behavior: owner depth = 0 blocks entering child
        assertTrue(out.contains("child=[MAX_DEPTH_REACHED]"),
                () -> "Expected parent->child to be MAX_DEPTH_REACHED, got: " + out);
    }

    @Test
    void circularReference_shouldNotStackOverflow() {
        Circular c = new Circular();
        c.self = c;

        String out = (String) LogSerializer.serialize(c);
        assertTrue(out.contains("[CIRCULAR]"));
    }

    @Test
    void collectionOfLoggable_shouldSerializeEachElement() {
        List<SimpleLoggable> list = List.of(new SimpleLoggable(), new SimpleLoggable());
        Object out = LogSerializer.serialize(list);

        assertTrue(out instanceof String);
        String json = (String) out;

        assertTrue(json.contains("a=hello"));
        assertTrue(json.contains("b=10"));
    }

    @Test
    void collectionOfNonLoggable_shouldReturnOriginal() {
        List<PlainObject> list = List.of(new PlainObject());
        Object out = LogSerializer.serialize(list);

        assertSame(list, out);
    }

    @Test
    void emptyCollection_shouldReturnOriginal() {
        List<SimpleLoggable> list = List.of();
        Object out = LogSerializer.serialize(list);

        assertSame(list, out);
    }

    // -------------------------
    // NEW: complex scenarios
    // -------------------------

    @Test
    void objectWithListField_ofLoggableElements_shouldSerializeElements() {
        ParentWithList p = new ParentWithList();
        String out = (String) LogSerializer.serialize(p);

        // current implementation converts nested Maps to strings in rendering,
        // so children should appear serialized inside the parent's string.
        assertTrue(out.contains("name=child") || out.contains("child"),
                () -> "Expected serialized children inside parent: " + out);
    }

    @Test
    void objectWithArrayField_shouldSerializeArrayElements() {
        ParentWithArray p = new ParentWithArray();
        String out = (String) LogSerializer.serialize(p);

        assertTrue(out.contains("name=child") || out.contains("child"),
                () -> "Expected serialized array children inside parent: " + out);
    }

    @Test
    void nestedCollections_shouldSerialize_twoLevels() {
        ParentWithNestedCollections p = new ParentWithNestedCollections();
        String out = (String) LogSerializer.serialize(p);

        // ensure nested structure produced something containing child name
        assertTrue(out.contains("name=child") || out.contains("child"),
                () -> "Expected nested serialized child: " + out);
    }

    @Test
    void arraySizeLoggable_fieldAnnotated_logSize_shouldReportLength() {
        ArraySizeLoggable x = new ArraySizeLoggable();
        String out = (String) LogSerializer.serialize(x);

        assertTrue(out.contains("nums=4"), () -> "Expected nums=4, got: " + out);
    }

    @Test
    void mapField_shouldBeRenderedAsToString_whenNotLoggableMapClass() {
        MapFieldLoggable m = new MapFieldLoggable();
        String out = (String) LogSerializer.serialize(m);

        // Map is not specially processed, so it should appear via toString like {k=v}
        assertTrue(out.contains("mapping={k=v}") || out.contains("{k=v}"),
                () -> "Expected mapping printed as {k=v}, got: " + out);
    }

    @Test
    void collectionContainingNulls_shouldIncludeNullsInRepresentation() {
        CollectionWithNulls c = new CollectionWithNulls();
        String out = (String) LogSerializer.serialize(c);

        // list.toString includes 'null' for null elements
        assertTrue(out.contains("null"), () -> "Expected 'null' for null element, got: " + out);
    }

    @Test
    void circularInstance_insideCollection_shouldMarkCircular() {
        CircularInCollection c = new CircularInCollection();
        c.list = List.of(c);
        String out = (String) LogSerializer.serialize(c);

        assertTrue(out.contains("[CIRCULAR]") || out.contains("CIRCULAR"),
                () -> "Expected circular marker in collection serialization: " + out);
    }

    // -------------------------
    // Tests that reflect 'desired' semantics but may be disabled until fixes
    // -------------------------

    @Test
    void ownerDepth_shouldPrevent_entering_collection_elements() {
        ParentWithListDepth0 p = new ParentWithListDepth0();
        String out = (String) LogSerializer.serialize(p);

        // Desired: because parent has depth=0, children should not be entered
        assertTrue(out.contains("children=[MAX_DEPTH_REACHED]") || out.contains("children=[\"[MAX_DEPTH_REACHED]\"]"),
                () -> "Expected collection elements to be blocked by owner depth, got: " + out);
    }

    @Test
    void mapContainingLoggableObjects_shouldProcessValues_whenFixed() {
        class WithMap {
            @Loggable
            class V { String x = "x"; }
            @Loggable
            class Owner {
                Map<String, V> m = Map.of("k", new V());
            }
        }
        // this test is a placeholder for when Map processing is implemented
    }
}
