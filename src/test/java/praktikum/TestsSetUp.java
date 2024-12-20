package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import data.Data;

import static data.Data.VALID_UNIQUE_USER_REQUEST_BODY;

public class TestsSetUp {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }
}
