package http;

import buissines_logic.DetectorServiceImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class NameApi {

    private final HttpServer httpServer;

    public NameApi(int serverPort) throws IOException {
        this.httpServer = HttpServer.create(new InetSocketAddress(serverPort), 0);
    }

    public void configure(String pathEndpoint, DetectorServiceImpl detectorService) {
        this.httpServer.createContext(pathEndpoint, (exchange -> {
            String requestMethod = exchange.getRequestMethod();

            if ("GET".equals(requestMethod))
                getHttpMethod(exchange, detectorService);
            if("POST".equals(requestMethod))
                postHttpMethod(exchange, detectorService);
            else
                exchange.sendResponseHeaders(405, -1); //Method not allowed at this endpoint
            exchange.close();
        }));
        this.httpServer.setExecutor(null);
    }

    public void start() {
        this.httpServer.start();
    }

    private void getHttpMethod(HttpExchange exchange, DetectorServiceImpl detectorService) throws IOException {
        byte[] allTokens = detectorService.getAllTokens();

        sendResponse(exchange, allTokens);
    }

    private void postHttpMethod(HttpExchange exchange, DetectorServiceImpl detectorService) throws IOException {
        String input = readInput(exchange);
        byte[] nameDetected = detectorService.detectName(input).getBytes();

        sendResponse(exchange, nameDetected);
    }

    private void sendResponse(HttpExchange exchange, byte[] out) throws IOException {
        exchange.sendResponseHeaders(200, out.length);
        OutputStream output = exchange.getResponseBody();
        output.write(out);
        output.flush();
    }

    private String readInput(HttpExchange exchange) {
        InputStream requestBody = exchange.getRequestBody();
        Scanner scanner = new Scanner(requestBody).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
