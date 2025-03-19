package controller;

import java.nio.charset.StandardCharsets;

import servlet.request.header.RequestHeader;
import servlet.response.header.ResponseHeader;
import util.HtmlReader;

public class Controller {

    public void index(RequestHeader requestHeader, ResponseHeader responseHeader){
        String responseBody = HtmlReader.readHtml("/index.html");
        responseHeader.response(responseBody.getBytes(StandardCharsets.UTF_8));


    }
}
