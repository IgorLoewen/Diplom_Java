package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Исключаем null-поля из JSON
public class OrderModel {
    private List<String> ingredients;

    // Конструктор без параметров (нужен для Jackson)
    public OrderModel() {
    }

    // Конструктор с параметрами
    public OrderModel(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // Геттеры и сеттеры
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        // Удобный вывод информации об объекте
        return "OrderModel{" +
                "ingredients=" + ingredients +
                '}';
    }
}
