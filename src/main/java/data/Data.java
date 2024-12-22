package data;

import java.util.Arrays;
import java.util.List;

public class Data {
    public static final String email = "fукe@dfgsdsdsw.ru";
    public static final String password = "12345678";
    public static final String name = "abcddcba";

    // Валидное тело для создания уникального пользователя
    public static final String VALID_UNIQUE_USER_REQUEST_BODY = String.format(
            "{ \"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\" }",
            email, password, name
    );

    // Валидное тело для логина курьера
    public static final String LOGIN_REQUEST_BODY = String.format(
            "{ \"email\": \"%s\", \"password\": \"%s\" }",
            email, password
    );

    // Список тел для параметризованных тестов, где одно из полей пустое
    public static final List<String> INVALID_USER_REQUEST_BODIES = Arrays.asList(
            String.format("{ \"email\": \"\", \"password\": \"%s\", \"name\": \"%s\" }", password, name),
            String.format("{ \"email\": \"%s\", \"password\": \"\", \"name\": \"%s\" }", email, name),
            String.format("{ \"email\": \"%s\", \"password\": \"%s\", \"name\": \"\" }", email, password)
    );

    // Список тел для проверки логина с неверным email и паролем
    public static final List<String> INVALID_LOGIN_REQUEST_BODIES = Arrays.asList(
            String.format("{ \"email\": \"wrongemail@test.com\", \"password\": \"%s\" }", password),
            String.format("{ \"email\": \"%s\", \"password\": \"wrongpassword\" }", email),
            String.format("{ \"email\": \"wrongemail@test.com\", \"password\": \"wrongpassword\" }") // Неверные email и password
    );

    // Список тел для параметризованных тестов, где одно из полей пустое
    public static final List<String> USER_DATA_UPDATE_BODIES = Arrays.asList(
            String.format("{ \"email\": \"pomenyali@pomenyalkin.ru\"}", email),
            String.format("{ \"password\": \"menyaem\"}", password),
            String.format("{ \"name\": \"Pomenyalkin\"}", name)
    );



}
