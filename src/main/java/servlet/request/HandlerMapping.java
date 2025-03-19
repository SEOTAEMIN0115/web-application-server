package servlet.request;

import java.io.OutputStream;

import controller.Controller;
import servlet.request.header.RequestHeader;
import servlet.response.header.ResponseHeader;

public class HandlerMapping {
    private final Controller controller;
    public HandlerMapping(Controller controller) {
        this.controller = controller;
    }

    public void handlerMapping(RequestHeader requestHeader, OutputStream out){
        if(requestHeader.getUrl().equals("/index.html")){
            ResponseHeader responseHeader = new ResponseHeader(out);
            controller.index(requestHeader, responseHeader);
        }
    }
}
