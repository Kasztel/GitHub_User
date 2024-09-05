package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();

        String username = InputHandler.getUsername();
        String response = RequestMaker.getRequest("https://api.github.com/users/"+username+"/repos",
                "Accept", "application/json", "Authorization", dotenv.get("AUTH_TOKEN"));


        if (response == null) {
            return;
        }

        JSONArray jsonArray = new JSONArray(response);

        if (jsonArray.isEmpty()) {
            System.out.println("Empty response");
        }

        int numberOfNonForkRepos = 0;
        System.out.println("Those are non-fork repositories:");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject repository = jsonArray.getJSONObject(i);
            if(repository.getBoolean("fork")) {
                continue;
            }
            System.out.println(repository.getString("name"));
            numberOfNonForkRepos++;
        }

        if (numberOfNonForkRepos == 0) {
            System.out.println("Only fork repositories");
        }
    }
}