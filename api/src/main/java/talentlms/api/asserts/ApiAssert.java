package talentlms.api.asserts;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import talentlms.api.entity.User;

@Slf4j
@Getter
public class ApiAssert {
    private Response response;

    public ApiAssert(Response response) {
        this.response = response;
    }

    public static ApiAssert assertThat(Response response) {
        return new ApiAssert(response);
    }

    public ApiAssert isCorrectStatusCode(Integer expectedStatusCode) {
        Assertions.assertThat(this.response.getStatusCode())
                .withFailMessage("Status code is not correct, Actual %s, Expected %s"
                        , this.response.getStatusCode()
                        , expectedStatusCode)
                .isEqualTo(expectedStatusCode);
        log.info("Status code is correct {}", expectedStatusCode);
        return this;
    }

    public UserAssert assertUser(User user) {
        return UserAssert.assertThat(user);
    }
}