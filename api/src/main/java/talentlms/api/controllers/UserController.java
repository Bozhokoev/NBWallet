package talentlms.api.controllers;

import lombok.Getter;
import talentlms.api.ApiRequest;
import talentlms.api.entity.User;

import java.util.HashMap;
import java.util.Map;

import static talentlms.api.TalentLMSEndpoints.*;



public class UserController extends ApiRequest {
    public UserController(String url) {
        super(url);
    }

    public User[] getUsers() {
        return super.get(getEndpoint(API, V1, USERS)).as(User[].class);
    }

    public User getUserBy(By by, String value) {
        HashMap<String, String> parameters = new HashMap<>() {{
            put(by.getKey(), value);
        }};
        return super.get(getEndpoint(API, V1, USERS
                , formatParameter(parameters))).as(User.class);
    }

    public User createUser(User user) {
        return super.post(getEndpoint(API, V1, USER_SIGNUP), user.toJson()).as(User.class);
    }

    public void deleteUser(String userId) {
        Map<String, String> params = new HashMap<>() {{
            put("user_id", userId);
            put("deleted_by_user_id", "1");
        }};
        super.post(getEndpoint(API, V1, DELETE_USER), params);
    }

    @Getter
    public enum By {
        ID("id"),
        USERNAME("username"),
        EMAIL("email");
        public final String key;

        By(String key) {
            this.key = key;
        }
    }
}