package client;

import io.restassured.response.Response;
import steps.StepsIngredients;

public class IngredientsClient {
    private final StepsIngredients stepsIngredients;

    public IngredientsClient(StepsIngredients stepsIngredients) {
        this.stepsIngredients = stepsIngredients;
    }

    public Response get() {
        return stepsIngredients.sendGetRequestIngredients();
    }
}