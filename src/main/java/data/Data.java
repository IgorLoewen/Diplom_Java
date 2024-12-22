package data;

import java.util.Arrays;
import java.util.List;

public class Data {
    public static final String email = "fукe@yпdfdfghвыsdfваыываf.ru";
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


}
