package com.betterbackendllc.backend;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GameController {

    private static final String SERVER_SEED = "<<SUPER-SECRET-SERVER-SEED>>";

    private static final int MAX_RANDOM_NUMBER = 20;

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        GameService gameService = new GameService(SERVER_SEED, MAX_RANDOM_NUMBER);

        port(8080);

        post("/spin", (request, response) -> {
            var userId = request.queryParams("userId");
            var clientSeed = request.queryParams("clientSeed");
            SpinResult result = gameService.spin(userId, clientSeed);

            return mapper.writeValueAsString(result);
        });

        get("/history", (request, response) -> {
            String userId = request.queryParams("userId");
            return mapper.writeValueAsString(gameService.getUserHistory(userId));
        });
    }
}
