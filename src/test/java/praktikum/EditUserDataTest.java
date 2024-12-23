package praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static data.Data.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class EditUserDataTest {

    public UserSteps userSteps;
    public Response response;
    public String token;


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userSteps = new UserSteps();
        response = userSteps.createUser(VALID_UNIQUE_USER_REQUEST_BODY);
        userSteps.getAccessToken(response);
        token = userSteps.accessToken;
    }

    @Test // Изменение поля email с авторизацией
    public void editUserDataWithAuthorizationEmailChange(){
        userSteps.editUserDataWithAuthorization(token,USER_DATA_UPDATE_BODIES.get(0))

                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("pomenyali@pomenyalkin.ru"))
                .body("user.name", equalTo(name));
    }

    @Test // Изменение поля пароль с авторизацией и проверка через логин,
          // так как смену пароля нельзя проверить в теле ответа!
    public void editUserDataWithAuthorizationPasswordChange(){

        String passwordJson = USER_DATA_UPDATE_BODIES.get(1);
        String newPassword = passwordJson.split(":")[1].replaceAll("[\"{} ]", "");


        userSteps.editUserDataWithAuthorization(token,USER_DATA_UPDATE_BODIES.get(1))

                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));

           //Тело с новым паролем для логина!
        String loginRequestBody = String.format(
                "{ \"email\": \"%s\", \"password\": \"%s\" }",
                email, newPassword
        );

            userSteps.loginUser(loginRequestBody)

                    .then()
                    .statusCode(200)
                    .body("success", equalTo(true));
    }

    @Test // Изменение поля имя с авторизацией
    public void editUserDataWithAuthorizationNameChange(){
        userSteps.editUserDataWithAuthorization(token,USER_DATA_UPDATE_BODIES.get(2))

                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo("Pomenyalkin"));
    }


    @Test // Изменение поля email без авторизации
    public void editUserDataWithoutAuthorizationEmailChange(){
        userSteps.editUserDataWithoutAuthorization(USER_DATA_UPDATE_BODIES.get(0))

                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));

    }

    @Test // Изменение поля пароль без авторизации
    public void editUserDataWithoutAuthorizationPasswordChange(){
        userSteps.editUserDataWithoutAuthorization(USER_DATA_UPDATE_BODIES.get(1))

                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));

    }

    @Test // Изменение поля имя без авторизации
    public void editUserDataWithoutAuthorizationNameChange(){
        userSteps.editUserDataWithoutAuthorization(USER_DATA_UPDATE_BODIES.get(2))

                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));

    }




    @After
    public void tearDown() {
        if (response != null) {
            userSteps.getAccessToken(response);
            userSteps.deleteUser();
            response = null;
            token = null;
        }
    }
}
