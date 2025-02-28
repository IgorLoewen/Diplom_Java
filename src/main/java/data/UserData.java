package data;

import com.github.javafaker.Faker;
import models.UserModel;

import java.util.Arrays;
import java.util.List;

import static data.OrderData.BASE_URL;

public class UserData {
    private static final Faker faker = new Faker();

    public static final String ORDER_URL = BASE_URL + "/api/orders";

    // Generating a unique user
    public static UserModel getValidUser() {
        return new UserModel(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                faker.name().firstName()
        );
    }

    // List of bodies for parameterized tests where one of the fields is empty
    public static List<UserModel> getInvalidUserRequests() {
        return Arrays.asList(
                new UserModel("", faker.internet().password(8, 16), faker.name().firstName()),
                new UserModel(faker.internet().emailAddress(), "", faker.name().firstName()),
                new UserModel(faker.internet().emailAddress(), faker.internet().password(8, 16), "")
        );
    }

    // List of bodies for login verification with incorrect data
    public static List<UserModel> getInvalidLoginRequests() {
        return Arrays.asList(
                new UserModel("wrongemail@test.com", faker.internet().password(8, 16), null),
                new UserModel(faker.internet().emailAddress(), "wrongpassword", null),
                new UserModel("wrongemail@test.com", "wrongpassword", null)
        );
    }

    // List of bodies for updating user data
    public static List<UserModel> getUserDataUpdateBodies() {
        return Arrays.asList(
                new UserModel(faker.internet().emailAddress(), null, null),
                new UserModel(null, faker.internet().password(8, 16), null),
                new UserModel(null, null, faker.name().lastName())
        );
    }

}