package nbwallet.api;


import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nbwallet.api.utils.EnvironmentManager;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
@Data
public abstract class ApiRequest {
    protected String url;
    protected RequestSpecification requestSpecification;
    protected Response response;
    private static String SLASH = "/";
    private String customerToken;

    public ApiRequest(String url) {
        this.url = url;
        this.requestSpecification = given()
                .baseUri(url)
                .accept("*/*");
        requestSpecification.log().all();
    }

    private void logResponse() {
        ApiRequest.log.warn("Response is:");
        ApiRequest.log.warn(getResponse().getBody().asPrettyString());
        ApiRequest.log.warn("Status code is {}", getResponse().getStatusCode());
    }

    protected static String getEndpoint(String... endpoints) {
        StringBuilder endPoint = new StringBuilder();
        for (String arg : endpoints) {
            endPoint.append(arg).append(SLASH);
        }
        return endPoint.substring(0, endPoint.length() - 1);
    }

    public String formatParameter(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }

    protected Response get(String endPoint) {
        ApiRequest.log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.getPort(endPoint))
                .auth().oauth2(customerToken)
                .accept(ContentType.ANY)
                .contentType(ContentType.JSON)
                .get(endPoint);
        logResponse();
        customerToken = response.jsonPath().getString("jwtToken");
        return this.response;
    }

    protected Response post(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.getPort(endPoint))
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
        logResponse();
        customerToken = response.jsonPath().getString("jwtToken");
        return this.response;
    }

    protected Response post(String endPoint, Map<String, String> params) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.getPort(endPoint))
                .formParams(params)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected Response postAuth(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.getPort(endPoint))
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(customerToken)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }
}