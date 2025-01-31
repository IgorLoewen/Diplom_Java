package data;

import models.UserModel;

import java.util.Arrays;
import java.util.List;

import static data.OrderData.BASE_URL;

public class UserData {

    // Order URL
    public static final String ORDER_URL = BASE_URL +"/api/orders";

    // Основные данные для пользователя
    public static final String EMAIL = "fукses@dfk4dfsw.ru";
    public static final String PASSWORD = "12345678";
    public static final String NAME = "abcddcbab";

    // Валидное тело для создания уникального пользователя
    public static UserModel getValidUser() {
        return new UserModel(EMAIL, PASSWORD, NAME);
    }

    // Валидное тело для логина пользователя
    public static UserModel getValidLogin() {
        return new UserModel(EMAIL, PASSWORD, null);
    }

    // Список тел для параметризованных тестов, где одно из полей пустое
    public static List<UserModel> getInvalidUserRequests() {
        return Arrays.asList(
                new UserModel("", PASSWORD, NAME),
                new UserModel(EMAIL, "", NAME),
                new UserModel(EMAIL, PASSWORD, "")
        );
    }

    // Список тел для проверки логина с неверными данными
    public static List<UserModel> getInvalidLoginRequests() {
        return Arrays.asList(
                new UserModel("wrongemail@test.com", PASSWORD, null),
                new UserModel(EMAIL, "wrongpassword", null),
                new UserModel("wrongemail@test.com", "wrongpassword", null)
        );
    }

    // Список тел для обновления данных пользователя
    public static List<UserModel> getUserDataUpdateBodies() {
        return Arrays.asList(
                new UserModel("pomenyali@pomenyalkin.ru", null, null),
                new UserModel(null, "menyaem", null),
                new UserModel(null, null, "Pomenyalkin")
        );
    }

    public static UserModel getEmailUpdate() {
        return new UserModel("pomenyali@pomenyalkin.ru", null, null);
    }

    public static UserModel getPasswordUpdate() {
        return new UserModel(null, "menyaem", null);
    }

    public static UserModel getNameUpdate() {
        return new UserModel(null, null, "Pomenyalkin");
    }



}
