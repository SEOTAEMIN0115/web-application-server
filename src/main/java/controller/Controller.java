package controller;

import java.nio.charset.StandardCharsets;

import db.DataBase;
import model.User;
import servlet.request.header.RequestHeader;
import servlet.response.enums.StatusCode;
import servlet.response.header.ResponseHeader;
import util.HtmlReader;

public class Controller {

    public void index(RequestHeader requestHeader, ResponseHeader responseHeader){
        String responseBody = HtmlReader.readHtml(requestHeader.getUrl());
        responseHeader.response(responseBody.getBytes(StandardCharsets.UTF_8), StatusCode.OK);
    }

    public void signUpPage(RequestHeader requestHeader, ResponseHeader responseHeader){
        String responseBody = HtmlReader.readHtml(requestHeader.getUrl());
        responseHeader.response(responseBody.getBytes(StandardCharsets.UTF_8), StatusCode.OK);
    }

    public void SignUpGet(RequestHeader requestHeader, ResponseHeader responseHeader){
        if(requestHeader.getGetBody() == null){
            responseHeader.response("올바른 값을 입력해주세요".getBytes(StandardCharsets.UTF_8), StatusCode.OK);
        }
        String name = requestHeader.getGetBody().get("name");
        String userId = requestHeader.getGetBody().get("userId");
        String password = requestHeader.getGetBody().get("password");
        String email = requestHeader.getGetBody().get("email");
        User user = new User(userId, password, name, email);
        DataBase.addUser(user);
    }

    public void SignUpPost(RequestHeader requestHeader, ResponseHeader responseHeader){
        String name = requestHeader.getPostBody().get("name");
        String userId = requestHeader.getPostBody().get("userId");
        String password = requestHeader.getPostBody().get("password");
        String email = requestHeader.getPostBody().get("email");
        User user = new User(userId, password, name, email);
        DataBase.addUser(user);
        String responseBody = HtmlReader.readHtml("/index.html");
        responseHeader.response(responseBody.getBytes(StandardCharsets.UTF_8), StatusCode.FOUND);
    }
}
