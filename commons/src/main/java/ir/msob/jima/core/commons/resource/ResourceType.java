package ir.msob.jima.core.commons.resource;

public class ResourceType {
    private ResourceType() {
    }
    public static final String RSOCKET_RESOURCE_TYPE = "rsocket";
    public static final String RESTFUL_RESOURCE_TYPE = "restful";
    public static final String GRAPHQL_RESTFUL_RESOURCE_TYPE = "graphql-restful";
    public static final String KAFKA_RESOURCE_TYPE = "kafka";
    public static final String GRPC_RESOURCE_TYPE = "grpc";
}
