package metodos;

import models.TipoMonedasDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionHTTP {

    private HttpClient client;

    private HttpRequest request;
    HttpResponse<String> response;

    public void conectarAPI(String direccion) {

        client = HttpClient.newHttpClient();

        request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> getResponse() {
        return response;
    }
}
