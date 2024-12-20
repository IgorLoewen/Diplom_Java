package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import steps.UserSteps;


import static data.Data.LOGIN_REQUEST_BODY;
import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;


public class TestsSetUp {

    private final UserSteps userSteps = new UserSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }


}
