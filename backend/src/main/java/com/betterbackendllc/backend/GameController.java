package com.betterbackendllc.backend;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
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

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers",
                    "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
            response.header("Access-Control-Allow-Credentials", "true");
        });

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

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
