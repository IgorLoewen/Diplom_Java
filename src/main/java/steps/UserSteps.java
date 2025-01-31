package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.UserModel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public String accessToken;
    public String refreshToken;

    @Step("Создание уникального пользователя с заданным телом запроса")
    public Response createUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post("/api/auth/register");
    }

    @Step("Логин пользователя с заданным телом запроса")
    public Response loginUser(UserModel userModel) {
        return given()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя по accessToken")
    public Response deleteUser() {
        return given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/auth/user");
    }

    @Step("Извлечение accessToken и refreshToken")
    public void getAccessToken(Response response) {
        this.accessToken = response.jsonPath().getString("accessToken");
        this.refreshToken = response.jsonPath().getString("refreshToken");
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
