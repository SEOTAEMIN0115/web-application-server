package servlet.request;

import java.io.*;
import java.net.Socket;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import servlet.request.header.RequestHandlerParser;

public class ServletRequestHandler extends Thread implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(ServletRequestHandler.class);
    private RequestHandlerParser requestHandlerParser;
    private Socket connection;

    public ServletRequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;

    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Map<String, List<String>> headers = new HashMap<>();
            while(true){
                String rawHeader = bufferedReader.readLine();
                if(rawHeader.isEmpty())
                    break;
                List<String> values = new LinkedList<>();
                String[] parsedHeaders = rawHeader.split(" ");
                String key = parsedHeaders[0];
                if (key.endsWith(":")){
                    key = key.substring(0, key.length() - 1);
                }
                values.addAll(Arrays.asList(parsedHeaders).subList(1, parsedHeaders.length));
                System.out.println(key);
                System.out.println(values);
                headers.put(key, values);
            }

            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = "Hello World".getBytes();
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
