package nbwallet.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nbwallet.api.utils.ApiConfigReader;

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

    public ApiRequest(String url) {
        this.url = url;
        String port = ApiConfigReader.getValue("identity.port");
        this.requestSpecification = given()
                .baseUri(url + port);
                // Constructing the base URI correctly
//                .auth()
//                .basic(ApiConfigReader.getValue("apiKey"), "");
    }

    private void logResponse() {
        log.warn("Response is:");
        log.warn(getResponse().getBody().asPrettyString());
        log.warn("Status code is {}", getResponse().getStatusCode());
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
        log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    protected Response post(String endPoint, String body) {
        log.info("Performed post {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected Response post(String endPoint, Map<String, String> params) {
        log.info("Performed post {}", endPoint);
        log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .formParams(params)
                .post(endPoint);
        logResponse();
        return this.response;
    }
}