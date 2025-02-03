package data;

import models.OrderModel;

import java.util.Arrays;
import java.util.List;

public class OrderData {


    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_URL = BASE_URL + "/api/auth/register";
    public static final String LOGIN_URL = BASE_URL + "/api/auth/login";
    public static final String AUTH_URL = BASE_URL + "/api/auth/user";


    //        List of order bodies with ingredients
    public static List<OrderModel> getOrderBodies() {
        return Arrays.asList(
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa6d")),
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa70")),
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa71", "61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa73")),
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa75", "61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa76", "61c0c5a71d1f82001bdaaa77")),
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa78")),
                new OrderModel(List.of("61c0c5a71d1f82001bdaaa79", "61c0c5a71d1f82001bdaaa7a", "61c0c5a71d1f82001bdaaa6e"))
        );
    }

    // Variable for an order without ingredients
    public static OrderModel getEmptyIngredients() {
        return new OrderModel(List.of());
    }

    // Variable with an invalid ingredient hash
    public static OrderModel getInvalidHashIngredient() {
        return new OrderModel(List.of("60d3b41abdacasdb0026a733c6"));
    }
}
