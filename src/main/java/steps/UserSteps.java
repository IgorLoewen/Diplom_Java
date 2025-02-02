package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.UserModel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static data.OrderData.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    public String accessToken;

    @Step("Creating a unique user with the specified request body")
    public Response createUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(REGISTER_URL);
    }

    @Step("User login with the specified request body")
    public Response loginUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(LOGIN_URL);
    }

    @Step("Deleting the user by accessToken")
    public Response deleteUser() {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .when()
                .delete(AUTH_URL);
    }

    @Step("Extracting accessToken")
    public void getAccessToken(Response response) {
        this.accessToken = response.jsonPath().getString("accessToken");
    }

    @Step("Updating user data with authorization")
    public Response editUserDataWithAuthorization(String accessToken, UserModel userModel) {
        return given()
                .header("Authorization",accessToken)
                .header("Content-Type", "application/json")
                .body(userModel)
                .when()
                .patch(AUTH_URL);
    }

    @Step("Updating user data without authorization")
    public Response editUserDataWithoutAuthorization(UserModel userModel) {
        return given()
                .header("Content-Type", "application/json")
                .body(userModel)
                .when()
                .patch(AUTH_URL);
    }

    @Step("Передача токена в localStorage браузера")
    public void setTokenInLocalStorage(WebDriver driver, String token) {
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('accessToken', arguments[0]);", token);
    }

    @Step("Передача refreshToken в localStorage браузера")
    public void setRefreshTokenInLocalStorage(WebDriver driver, String refreshToken) {
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('refreshToken', arguments[0]);", refreshToken);
    }

}

