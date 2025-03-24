package servlet.response.header;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import servlet.request.ServletRequestHandler;
import servlet.response.enums.StatusCode;

public class ResponseHeader {

    private static final Logger log = LoggerFactory.getLogger(ServletRequestHandler.class);
    public OutputStream outputStream;

    public ResponseHeader(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void response(byte[] body, StatusCode statusCode){
        if (statusCode == StatusCode.OK){
            DataOutputStream dos = new DataOutputStream(this.outputStream);
            response200Header(dos, body.length);
            responseBody(dos, body);
        }
        else if(statusCode == StatusCode.FOUND){
            DataOutputStream dos = new DataOutputStream(this.outputStream);
            response302Header(dos, body.length);
            responseBody(dos, body);
        }

    }

    public void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void response302Header(DataOutputStream dos, int lengthOfBodyContent){
        try {
            dos.writeBytes("HTTP/1.1 302 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}