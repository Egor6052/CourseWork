package Package;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class HttpHandlerServer implements HttpHandler {
    static int requestCounter = 0;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] requestParams = null;
//         if ("GET".equals(httpExchange.getRequestMethod())) {
//             requestParams = getRequestParams(httpExchange);
//         }
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParams = getRequestParams(httpExchange);
        }
        returnResponse(httpExchange, requestParams);
    }

    /**
     * Метод получения параметров;
     */
    private String[] getRequestParams(HttpExchange httpExchange) {
        String parameters = httpExchange.getRequestURI().toString().split("\\?")[1];
        String[] params = parameters.split("&");

      for (String param : params) {
         System.out.println(param.split("=")[0] + " -> " + param.split("=")[1]);
      }
        return params;
    }

    /**
     * Метод отправки ответа на полученый запрос;
     */
    private void returnResponse(HttpExchange httpExchange, String[] requestParamValues) throws IOException {
        requestCounter++;
        System.out.println("Request received: " + requestCounter);
        OutputStream outputStream = httpExchange.getResponseBody();

        StringBuilder response = new StringBuilder("{");
        String info = new BufferedReader(new FileReader("src/Package/Database.txt")).readLine();

        response.append(new BufferedReader(new FileReader("src/Package/Database.txt")).readLine());
        
        httpExchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
