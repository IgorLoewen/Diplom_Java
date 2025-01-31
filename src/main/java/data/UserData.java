package data;

import com.github.javafaker.Faker;
import models.UserModel;


public class UserData {
    private static final Faker faker = new Faker();

    // Генерация уникального пользователя
    public static UserModel getValidUser() {
        return new UserModel(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                faker.name().firstName()
        );
    }
}