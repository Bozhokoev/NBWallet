package nbwallet.api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nbwallet.api.environmentmanager.EndpointFactory;
import nbwallet.api.environmentmanager.EndpointHandler;

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
    private String managerToken;

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
                .port(getPort(endPoint))
                .auth().oauth2(customerToken)
                .contentType(ContentType.JSON)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    protected Response getManager(String endPoint) {
        log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .auth()
                .oauth2(managerToken)
                .contentType(ContentType.JSON)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    protected void post(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
        logResponse();
        if (body.contains("manager")) {
            managerToken = response.jsonPath().getString("jwtToken");
        }
        customerToken = response.jsonPath().getString("jwtToken");
    }

    protected Response post(String endPoint, Map<String, String> params) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
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
                .port(getPort(endPoint))
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(customerToken)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected void postAccountTL(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .auth().oauth2(customerToken)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
        logResponse();
    }

    protected void putAccountTL(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .accept("*/*")
                .port(getPort(endPoint))
                .auth().oauth2(customerToken)
                .contentType(ContentType.JSON)
                .body(body)
                .put(endPoint);
        logResponse();
    }

    protected void deleteAccountTL(String endPoint, int id) {
        ApiRequest.log.info("Performed post {}", endPoint);
        String endpointWithId = endPoint + "/" + id;
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .auth().oauth2(customerToken)
                .delete(endpointWithId);
        logResponse();
    }


    protected void deleteAccountPlan(String endPoint, int id) {
        ApiRequest.log.info("Performed post {}", endPoint);
        String endpointWithId = endPoint + "/" + id;
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .auth().oauth2(managerToken)
                .delete(endpointWithId);
        logResponse();
    }
    protected Response postBody(String endPoint, String body) {
        ApiRequest.log.info("Performed post {}", endPoint);
        ApiRequest.log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(managerToken)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected void putBodyAndPath(String endPoint, String body) {
        log.info("Performed post {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(getPort(endPoint))
                .auth()
                .oauth2(managerToken)
                .contentType(ContentType.JSON)
                .body(body)
                .put(endPoint);
        logResponse();
    }

    public static int getPort(String endPoint) {
        EndpointHandler handler = EndpointFactory.getHandler(endPoint);
        return handler.getPort();
    }
}