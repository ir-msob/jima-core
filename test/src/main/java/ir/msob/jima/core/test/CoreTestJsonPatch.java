package ir.msob.jima.core.test;

import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import ir.msob.jima.core.commons.model.characteristic.Characteristic;
import ir.msob.jima.core.commons.model.relateddomain.RelatedDomain;
import ir.msob.jima.core.commons.model.relatedparty.RelatedParty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ir.msob.jima.core.test.CoreTestData.*;

/**
 * This class, CoreJsonPatch, provides methods for generating JSON Patch operations used in testing scenarios.
 * It includes methods for creating operations related to characteristics, related domains, and related parties.
 */
public class CoreTestJsonPatch {

    public static final String RELATED_PARTIES_PATH = "/relatedParties/0/%s";
    public static final String RELATED_DOMAINS_PATH = "/relatedDomains/0/%s";
    public static final String CHARACTERISTICS_PATH = "/characteristics/0/%s";

    private CoreTestJsonPatch() {
    }

    /**
     * Generates a list of JSON Patch operations for updating characteristics.
     *
     * @return A list of JSON Patch operations.
     * @throws JsonPointerException If there's an issue with JSON pointers.
     */
    public static List<JsonPatchOperation> CHARACTERISTICS_JSON_PATCH_OPERATION() throws JsonPointerException {
        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(new ReplaceOperation(new JsonPointer(String.format(CHARACTERISTICS_PATH, Characteristic.FN.key)), TextNode.valueOf(UPDATED_STRING)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(CHARACTERISTICS_PATH, Characteristic.FN.value)), IntNode.valueOf(UPDATED_INTEGER)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(CHARACTERISTICS_PATH, Characteristic.FN.dataType)), TextNode.valueOf(UPDATED_DATA_TYPE.name())));
        return operations;
    }

    /**
     * Generates a list of JSON Patch operations for updating related domains.
     *
     * @param id   The identifier of the related domain.
     * @param <ID> The type of the identifier.
     * @return A list of JSON Patch operations.
     * @throws JsonPointerException If there's an issue with JSON pointers.
     */
    public static <ID extends Comparable<ID> & Serializable> List<JsonPatchOperation> RELATED_DOMAINS_JSON_PATCH_OPERATION(ID id) throws JsonPointerException {
        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_DOMAINS_PATH, RelatedDomain.FN.relatedType)), TextNode.valueOf(UPDATED_STRING)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_DOMAINS_PATH, RelatedDomain.FN.relatedId)), TextNode.valueOf(id.toString())));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_DOMAINS_PATH, RelatedDomain.FN.role)), TextNode.valueOf(UPDATED_STRING)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_DOMAINS_PATH, RelatedDomain.FN.referredType)), TextNode.valueOf(UPDATED_STRING)));
        return operations;
    }

    /**
     * Generates a list of JSON Patch operations for updating related parties.
     *
     * @param id   The identifier of the related party.
     * @param <ID> The type of the identifier.
     * @return A list of JSON Patch operations.
     * @throws JsonPointerException If there's an issue with JSON pointers.
     */
    public static <ID extends Comparable<ID> & Serializable> List<JsonPatchOperation> RELATED_PARTIES_JSON_PATCH_OPERATION(ID id) throws JsonPointerException {
        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_PARTIES_PATH, RelatedParty.FN.relatedType)), TextNode.valueOf(UPDATED_STRING)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_PARTIES_PATH, RelatedParty.FN.relatedId)), TextNode.valueOf(id.toString())));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_PARTIES_PATH, RelatedParty.FN.role)), TextNode.valueOf(UPDATED_STRING)));
        operations.add(new ReplaceOperation(new JsonPointer(String.format(RELATED_PARTIES_PATH, RelatedParty.FN.referredType)), TextNode.valueOf(UPDATED_STRING)));
        return operations;
    }
}
