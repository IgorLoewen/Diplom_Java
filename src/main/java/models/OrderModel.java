package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON
public class OrderModel {
    private List<String> ingredients;

    // No-args constructor (required for Jackson)
    public OrderModel() {
    }

    // Constructor with parameters
    public OrderModel(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // Getters and setters
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {

        return "OrderModel{" +
                "ingredients=" + ingredients +
                '}';
    }
}
