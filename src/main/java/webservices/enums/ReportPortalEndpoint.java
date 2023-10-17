package webservices.enums;


public enum ReportPortalEndpoint {
    GET_HEALTH("/health");

    private String endpoint;

    ReportPortalEndpoint(String endpoint) {this.endpoint = endpoint;}

    public String getEndpoint() { return endpoint;}
}
