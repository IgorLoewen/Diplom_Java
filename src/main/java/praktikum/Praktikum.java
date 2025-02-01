package praktikum;

import java.util.List;

public class Praktikum {

    public static void main(String[] args) {
        // Initializing the database
        Database database = new Database();

        // Create a new burger
        Burger burger = new Burger();

        // Retrieve the list of available buns from the database
        List<Bun> buns = database.availableBuns();

        // Retrieve the list of available ingredients from the database
        List<Ingredient> ingredients = database.availableIngredients();

        // Assemble the burger
        burger.setBuns(buns.get(0));

        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));

        // Move the ingredient layer
        burger.moveIngredient(2, 1);

        // Remove the ingredient
        burger.removeIngredient(3);

        // Print the burger recipe
        System.out.println(burger.getReceipt());
    }

}