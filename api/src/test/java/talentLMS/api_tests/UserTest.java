package talentLMS.api_tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talentlms.api.asserts.ApiAssert;
import talentlms.api.controllers.UserController;
import talentlms.api.entity.User;
import talentlms.api.utils.EntityManager;

import java.util.Arrays;

public class UserTest extends BaseApiTest {
    UserController userController;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        userController = application.getUserController();
    }

    @Test
    public void userDelete() {
        User[] users = userController.getUsers();
        System.out.println(Arrays.toString(users));
        userController.deleteUser(users[0].getId());
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
    }

    @Test
    public void createUser() {
        User[] users = userController.getUsers();
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
        if (users.length == 5) {
            userController.deleteUser(users[users.length - 1].getId());
            ApiAssert.assertThat(userController.getResponse())
                    .isCorrectStatusCode(200);
        }
        User expectedUser = EntityManager.generateUser();
        User actualUser = userController.createUser(expectedUser);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser(actualUser)
                .isEqualTo(expectedUser);
    }
}