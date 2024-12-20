package praktikum;


import io.qameta.allure.junit4.AllureJunit4;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import data.Data;

import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import data.Data;
import steps.UserSteps;

public class CreateUserTest extends TestsSetUp{

    private final UserSteps userSteps = new UserSteps();

    @Test
    public void uniqueUserCreating() {

        userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY)
                .then().statusCode(200).body("success", equalTo(true));
    }
}
