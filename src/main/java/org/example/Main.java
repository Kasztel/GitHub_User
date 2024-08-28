package org.example;
import io.github.cdimascio.dotenv.Dotenv;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username of programmist: ");
        String username = scanner.nextLine();

        Dotenv dotenv = Dotenv.load();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/users/" + username + "/repos"))
                .header("Accept", "application/json")
                .header("Authorization", dotenv.get("AUTH_TOKEN"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}