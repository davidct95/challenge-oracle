import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import metodos.ConexionHTTP;
import models.TipoMonedasDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ConexionHTTP conexionHTTP = new ConexionHTTP();
        HttpResponse<String> response;

        int opcionElegida;
        double valorAConvertir;
        double valorConvertido;
        String baseCode = "";
        String targetCode = "";

        Gson gson = new GsonBuilder()
                .create();

        while (true) {

            System.out.println("\n*********************************************");
            System.out.println("Sea bienvenido al conversor de monedas\n");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elige una opción válida");
            System.out.println("*********************************************\n");

            opcionElegida = teclado.nextInt();

            switch (opcionElegida) {
                case 1:
                    baseCode = "USD";
                    targetCode = "ARS";
                    break;
                case 2:
                    baseCode = "ARS";
                    targetCode = "USD";
                    break;
                case 3:
                    baseCode = "USD";
                    targetCode = "BRL";
                    break;
                case 4:
                    baseCode = "BRL";
                    targetCode = "USD";
                    break;
                case 5:
                    baseCode = "USD";
                    targetCode = "COP";
                    break;
                case 6:
                    baseCode = "COP";
                    targetCode = "USD";
                    break;
                case 7:
                    break;
            }

            if (opcionElegida == 7){
                break;
            }

            System.out.println("Ingrese el valor que desee convertir");

            valorAConvertir = teclado.nextDouble();

            String direccion = "https://v6.exchangerate-api.com/v6/0357ad1dee010a876d2b0e5c/pair/" + baseCode + "/" + targetCode;

            //Realizamos la conexión a la API
            conexionHTTP.conectarAPI(direccion);

            //Obtenemos la response
            response = conexionHTTP.getResponse();

            String json = response.body();

            TipoMonedasDTO tipoMonedasDTO = gson.fromJson(json, TipoMonedasDTO.class);

            valorConvertido = valorAConvertir * tipoMonedasDTO.conversion_rate();

            System.out.println("El valor " + valorAConvertir + "[" + baseCode + "] corresponde al valor final de =>> " + valorConvertido + "[" + targetCode + "]");
        }
    }
}
