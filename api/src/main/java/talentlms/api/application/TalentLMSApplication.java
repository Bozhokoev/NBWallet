package talentlms.api.application;

import lombok.Data;
import talentlms.api.controllers.UserController;


@Data
public class TalentLMSApplication {
    private UserController userController;

    public TalentLMSApplication(String url) {
        this.userController = new UserController(url);
    }
}