package data;

import models.UserModel;

import java.util.Arrays;
import java.util.List;

public class UserData {

    // Основные данные для пользователя
    public static final String EMAIL = "rwdd@fg.ru";
    public static final String PASSWORD = "12345dfg678";
    public static final String NAME = "abcddcdfgba";

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
}