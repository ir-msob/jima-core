package ir.msob.jima.core.commons.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PaginationUtilTest {

    @Test
    void preparePage_ValidJsonNode_ReturnsPageImpl() throws IOException {
        // Arrange
        String jsonString = "{\"content\":[],\"number\":0,\"size\":10,\"totalElements\":0}";
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.setCodec(new ObjectMapper());
        JsonNode jsonNode = jsonParser.readValueAsTree();

        // Act
        PageImpl<Serializable> page = PaginationUtil.preparePage(jsonNode, jsonParser, Serializable.class);

        // Assert
        assertNotNull(page);
        assertEquals(0, page.getNumber());
        assertEquals(10, page.getSize());
        assertEquals(0, page.getTotalElements());
    }

    @Test
    void preparePageableFromPage_ValidJsonNode_ReturnsPageable() {
        // Arrange
        String jsonString = "{\"number\":0,\"size\":10}";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        Pageable pageable = PaginationUtil.preparePageableFromPage(jsonNode);

        // Assert
        assertNotNull(pageable);
        assertEquals(0, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize());
    }

    @Test
    void preparePageable_ValidJsonNode_ReturnsPageable() {
        // Arrange
        String jsonString = "{\"sort\":[{\"direction\":\"ASC\",\"property\":\"name\"}],\"pageNumber\":0,\"pageSize\":10}";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        Pageable pageable = PaginationUtil.preparePageable(jsonNode);

        // Assert
        assertNotNull(pageable);
        assertEquals(0, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize());
        assertEquals(Sort.Direction.ASC, Objects.requireNonNull(pageable.getSort().getOrderFor("name")).getDirection());
    }

    @Test
    void prepareContent_ValidJsonNode_ReturnsList() throws IOException {
        // Arrange
        String jsonString = "[{\"name\":\"item1\"},{\"name\":\"item2\"}]";
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.setCodec(new ObjectMapper());
        JsonNode jsonNode = jsonParser.readValueAsTree();

        // Act
        var content = PaginationUtil.prepareContent(jsonNode, jsonParser, Serializable.class);

        // Assert
        assertNotNull(content);
        assertEquals(2, content.size());
        assertEquals("item1", ((LinkedHashMap<?, ?>) content.get(0)).get("name"));
        assertEquals("item2", ((LinkedHashMap<?, ?>) content.get(1)).get("name"));
    }

    @Test
    void prepareOrders_ValidJsonNode_ReturnsList() {
        // Arrange
        String jsonString = "[{\"direction\":\"ASC\",\"property\":\"name\"},{\"direction\":\"DESC\",\"property\":\"date\"}]";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        var orders = PaginationUtil.prepareOrders(jsonNode);

        // Assert
        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals("name", orders.get(0).getProperty());
        assertEquals(Sort.Direction.ASC, orders.get(0).getDirection());
        assertEquals("date", orders.get(1).getProperty());
        assertEquals(Sort.Direction.DESC, orders.get(1).getDirection());
    }

    @Test
    void prepareOrder_ValidJsonNode_ReturnsSortOrder() {
        // Arrange
        String jsonString = "{\"direction\":\"ASC\",\"property\":\"name\",\"ignoreCase\":true,\"nullHandling\":\"NULLS_FIRST\"}";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        var order = PaginationUtil.prepareOrder(Sort.NullHandling.NULLS_FIRST, Sort.Direction.ASC, jsonNode);

        // Assert
        assertNotNull(order);
        assertEquals("name", order.getProperty());
        assertEquals(Sort.Direction.ASC, order.getDirection());
        assertTrue(order.isIgnoreCase());
        assertEquals(Sort.NullHandling.NULLS_FIRST, order.getNullHandling());
    }

    @Test
    void asDirection_ValidJsonNode_ReturnsSortDirection() {
        // Arrange
        String jsonString = "\"DESC\"";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        var direction = PaginationUtil.asDirection(jsonNode);

        // Assert
        assertNotNull(direction);
        assertEquals(Sort.Direction.DESC, direction);
    }

    @Test
    void asNullHandling_ValidJsonNode_ReturnsSortNullHandling() {
        // Arrange
        String jsonString = "\"NULLS_LAST\"";
        JsonNode jsonNode = createJsonNode(jsonString);

        // Act
        var nullHandling = PaginationUtil.asNullHandling(jsonNode);

        // Assert
        assertNotNull(nullHandling);
        assertEquals(Sort.NullHandling.NULLS_LAST, nullHandling);
    }

    private JsonNode createJsonNode(String jsonString) {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser;
        try {
            jsonParser = jsonFactory.createParser(jsonString);
            jsonParser.setCodec(new ObjectMapper());
            return jsonParser.readValueAsTree();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
