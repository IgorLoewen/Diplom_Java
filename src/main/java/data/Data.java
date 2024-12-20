package data;

public class Data {
    public static final String email = "tesd5fgxsdfta@yandex.ru";
    public static final String password = "12345678";
    public static final String name = "abcddcba";

    // Валидное тело для создания уникального пользователя
    public static final String VALID_UNIQUE_USER_REQUEST_BODY = String.format(
            "{ \"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\" }",
            email, password, name
    );
}
