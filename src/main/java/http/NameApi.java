package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class NameApi {

    public static HttpServer create(int serverPort) throws IOException {
        return HttpServer.create(new InetSocketAddress(serverPort), 0);
    }

    public static void configure(HttpServer server, String pathEndpoint, DetectorService detectorService) throws IOException {
        server.createContext(pathEndpoint, (exchange -> {
            String requestMethod = exchange.getRequestMethod();

            if ("GET".equals(requestMethod))
                getHttpMethod(exchange, detectorService);
            if("POST".equals(requestMethod))
                postHttpMethod(exchange, detectorService);
            else
                exchange.sendResponseHeaders(405, -1); //Method not allowed at this endpoint
            exchange.close();
        }));
        server.setExecutor(null);
    }

    private static void getHttpMethod(HttpExchange exchange, DetectorService detectorService) throws IOException {
        byte[] maleTokens = detectorService.getMaleTokens();
        byte[] femaleTokens = detectorService.getFemaleTokens();
        byte[] allTokens = concatByteArray(maleTokens, femaleTokens);

        sendResponse(exchange, allTokens);
    }

    private static void postHttpMethod(HttpExchange exchange, DetectorService detectorService) throws IOException {
        String input = readInput(exchange);
        byte[] nameDetected = detectorService.detectName(input).getBytes();

        sendResponse(exchange, nameDetected);
    }

    private static void sendResponse(HttpExchange exchange, byte[] out) throws IOException {
        exchange.sendResponseHeaders(200, out.length);
        OutputStream output = exchange.getResponseBody();
        output.write(out);
        output.flush();
    }

    private static byte[] concatByteArray(byte[] byte1, byte[] byte2) {
        return ByteBuffer.allocate(byte1.length + byte2.length)
                .put(byte1)
                .put(byte2)
                .array();
    }

    private static String readInput(HttpExchange exchange) {
        InputStream requestBody = exchange.getRequestBody();
        Scanner scanner = new Scanner(requestBody).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
