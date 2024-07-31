package ir.msob.jima.core.commons.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling pagination-related tasks.
 */
public class PaginationUtil {

    private PaginationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Prepare a {@link PageImpl} from a {@link JsonNode}.
     *
     * @param jsonNode   The JSON representation of the page.
     * @param jsonParser The JSON parser.
     * @return A {@link PageImpl} instance.
     * @throws IOException If an error occurs during JSON parsing.
     */
    public static <M extends Serializable> PageImpl<M> preparePage(JsonNode jsonNode, JsonParser jsonParser, Class<M> dtoClass) throws IOException {
        List<M> content = prepareContent(jsonNode.get("content"), jsonParser, dtoClass);
        Pageable pageable = preparePageableFromPage(jsonNode);
        long totalElements = asLong(jsonNode.get("totalElements"));
        return new PageImpl<>(content, pageable, totalElements);
    }

    /**
     * Prepare a {@link Pageable} instance from a {@link JsonNode}.
     *
     * @param jsonNode The JSON representation of the page.
     * @return A {@link Pageable} instance.
     */
    public static Pageable preparePageableFromPage(JsonNode jsonNode) {
        int pageNumber = asInteger(jsonNode.get("number"));
        int pageSize = asInteger(jsonNode.get("size"));
        return PageRequest.of(pageNumber, pageSize);
    }

    /**
     * Prepare a {@link Pageable} instance from a {@link JsonNode}.
     *
     * @param jsonNode The JSON representation of the page.
     * @return A {@link Pageable} instance.
     */
    public static Pageable preparePageable(JsonNode jsonNode) {
        List<Sort.Order> orderList = prepareOrders(jsonNode.get("sort"));
        int pageNumber = asInteger(jsonNode.get("pageNumber"));
        int pageSize = asInteger(jsonNode.get("pageSize"));
        return PageRequest.of(pageNumber, pageSize, Sort.by(orderList));
    }

    /**
     * Prepare a list of content objects from a {@link JsonNode}.
     *
     * @param jsonNode   The JSON representation of the content.
     * @param jsonParser The JSON parser.
     * @return A list of content objects.
     * @throws IOException If an error occurs during JSON parsing.
     */
    public static <M extends Serializable> List<M> prepareContent(JsonNode jsonNode, JsonParser jsonParser, Class<M> dtoClass) throws IOException {
        List<M> result = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode itemJsonNode : jsonNode) {
                M item = jsonParser.getCodec().treeToValue(itemJsonNode, dtoClass);
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Prepare a list of {@link Sort.Order} from a {@link JsonNode}.
     *
     * @param jsonNode The JSON representation of the sort orders.
     * @return A list of {@link Sort.Order}.
     */
    public static List<Sort.Order> prepareOrders(JsonNode jsonNode) {
        List<Sort.Order> orderList = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode orderJsonNode : jsonNode) {
                Sort.Direction direction = asDirection(orderJsonNode.get("direction"));
                Sort.NullHandling nullHandling = asNullHandling(orderJsonNode.get("nullHandling"));
                Sort.Order order = prepareOrder(nullHandling, direction, orderJsonNode);
                orderList.add(order);
            }
        }
        return orderList;
    }

    /**
     * Prepare a {@link Sort.Order} from a {@link JsonNode}.
     *
     * @param nullHandling  The null handling strategy.
     * @param direction     The sort direction.
     * @param orderJsonNode The JSON representation of the sort order.
     * @return A {@link Sort.Order} instance.
     */
    public static Sort.Order prepareOrder(Sort.NullHandling nullHandling, Sort.Direction direction, @NotNull JsonNode orderJsonNode) {
        String property = asString(orderJsonNode.get("property"));
        boolean ignoreCase = asBoolean(orderJsonNode.get("ignoreCase"));
        return new Sort.Order(direction, property, ignoreCase, nullHandling);
    }

    /**
     * Convert a {@link JsonNode} to a {@link String}.
     *
     * @param jsonNode The JSON node.
     * @return The string representation of the JSON node.
     */
    private static String asString(JsonNode jsonNode) {
        return (jsonNode != null) ? jsonNode.asText() : null;
    }

    /**
     * Convert a {@link JsonNode} to an {@link Integer}.
     *
     * @param jsonNode The JSON node.
     * @return The integer representation of the JSON node.
     */
    private static Integer asInteger(JsonNode jsonNode) {
        return (jsonNode != null) ? jsonNode.asInt() : null;
    }

    /**
     * Convert a {@link JsonNode} to a {@link Long}.
     *
     * @param jsonNode The JSON node.
     * @return The long representation of the JSON node.
     */
    private static Long asLong(JsonNode jsonNode) {
        return (jsonNode != null) ? jsonNode.asLong() : null;
    }

    /**
     * Convert a {@link JsonNode} to a {@code boolean}.
     *
     * @param jsonNode The JSON node.
     * @return The boolean representation of the JSON node. Returns false if the JSON node is null.
     */
    private static boolean asBoolean(JsonNode jsonNode) {
        return jsonNode != null && jsonNode.asBoolean();
    }

    /**
     * Convert a {@link JsonNode} to a {@link Sort.Direction}.
     *
     * @param jsonNode The JSON node.
     * @return The sort direction representation of the JSON node.
     */
    public static Sort.Direction asDirection(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        String directionString = jsonNode.asText();
        return (directionString.equalsIgnoreCase(Sort.Direction.DESC.name())) ? Sort.Direction.DESC :
                (directionString.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.Direction.ASC : null;
    }

    /**
     * Convert a {@link JsonNode} to a {@link Sort.NullHandling}.
     *
     * @param jsonNode The JSON node.
     * @return The null handling strategy representation of the JSON node.
     */
    public static Sort.NullHandling asNullHandling(JsonNode jsonNode) {
        if (jsonNode == null) {
            return Sort.NullHandling.NATIVE;
        }
        String nullHandlingString = jsonNode.asText();
        return (nullHandlingString.equalsIgnoreCase(Sort.NullHandling.NATIVE.name())) ? Sort.NullHandling.NATIVE :
                (nullHandlingString.equalsIgnoreCase(Sort.NullHandling.NULLS_FIRST.name())) ? Sort.NullHandling.NULLS_FIRST :
                        (nullHandlingString.equalsIgnoreCase(Sort.NullHandling.NULLS_LAST.name())) ? Sort.NullHandling.NULLS_LAST :
                                Sort.NullHandling.NATIVE;
    }
}
