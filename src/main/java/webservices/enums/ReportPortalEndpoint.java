package webservices.enums;


public enum ReportPortalEndpoint {
    GET_HEALTH("/health"),
    GET_LAUNCHERS("/api/v1/default_personal/launch"),
    GET_LAUNCHER_BY_ID("/api/v1/default_personal/launch/{launchId}");

    private String endpoint;

    ReportPortalEndpoint(String endpoint) {this.endpoint = endpoint;}

    public String getEndpoint() { return endpoint;}
}
