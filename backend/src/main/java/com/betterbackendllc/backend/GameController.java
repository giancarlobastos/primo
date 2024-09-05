package com.betterbackendllc.backend;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GameController {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        port(8080);

        post("/spin", (request, response) -> {
            var userId = request.queryParams("userId");
            var clientSeed = request.queryParams("clientSeed");
            SpinResult result = GameService.spin(userId, clientSeed);

            return mapper.writeValueAsString(result);
        });

        get("/history", (request, response) -> {
            String userId = request.queryParams("userId");
            return mapper.writeValueAsString(GameService.getUserHistory(userId));
        });
    }
}
