package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.LOGIN_REQUEST_BODY;
import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest extends TestsSetUp{

    private final UserSteps userSteps = new UserSteps();


    @Test
    public void uniqueUserCreating() {
        userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY)
                .then().statusCode(200).body("success", equalTo(true));
        userSteps.deleteUser();

    }
}
