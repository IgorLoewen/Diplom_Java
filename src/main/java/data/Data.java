package data;

import java.util.Arrays;
import java.util.List;

public class Data {
    public static final String email = "fукe@dfkdfdsw.ru";
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

    // Список тел заказов с ингредиентами
    public static final List<String> ORDER_BODIES = Arrays.asList(
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\"] }",
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa6f\", \"61c0c5a71d1f82001bdaaa70\"] }",
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa71\", \"61c0c5a71d1f82001bdaaa72\", \"61c0c5a71d1f82001bdaaa73\"] }",
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa74\", \"61c0c5a71d1f82001bdaaa75\", \"61c0c5a71d1f82001bdaaa6c\", \"61c0c5a71d1f82001bdaaa76\", \"61c0c5a71d1f82001bdaaa77\"] }",
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa78\"] }",
            "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa79\", \"61c0c5a71d1f82001bdaaa7a\", \"61c0c5a71d1f82001bdaaa6e\"] }"
    );

    // Переменная для заказа без ингридиентов
    public static final String EMPTY_INGREDIENTS = "{ \"ingredients\": [] }";

    // Переменная с невалидным Hash ингредиента
    public static final String INVALID_HASH_INGREDIENT = "{ \"ingredients\": [\"60d3b41abdacasdb0026a733c6\"] }";



}
