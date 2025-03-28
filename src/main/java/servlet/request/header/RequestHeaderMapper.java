package servlet.request.header;

import java.util.List;
import java.util.Map;
import util.HttpRequestUtils;

import servlet.request.header.enums.Method;

public class RequestHeaderMapper {
    public RequestHeader map(Map<String, List<String>> parsedHeaders){
        Method method = getMethod(parsedHeaders);
        RequestHeader.Builder builder = new RequestHeader.Builder()
            .setHeaders(parsedHeaders)
            .setMethod(method)
            .setCookie(parsedHeaders.get("Cookie"))
            .setUrls(getUrls(parsedHeaders, method))
            .setHost(parsedHeaders.get("Host").get(0))
            .setConnection(parsedHeaders.get("Connection").get(0))
            .setAccept(parsedHeaders.get("Accept"));


        String postBody = parsedHeaders.get("body").get(0);

        if (method == Method.POST && postBody != null){
            builder.setPostBody(mapPostBody(postBody));
        }

        return builder.build();
    }

    private Method getMethod(Map<String, List<String>> parsedHeaders){
        if (parsedHeaders.containsKey("GET") || parsedHeaders.containsKey("POST") || parsedHeaders.containsKey("PUT")
            || parsedHeaders.containsKey("DELETE")){
            if(parsedHeaders.containsKey("GET"))
                return Method.valueOf("GET");
            else if (parsedHeaders.containsKey("POST"))
                return Method.valueOf("POST");
            else if(parsedHeaders.containsKey("PUT"))
                return Method.valueOf("PUT");
            else if(parsedHeaders.containsKey("DELETE"))
                return Method.valueOf("DELETE");
        }
        return null;
    }

    private String getUrls(Map<String, List<String>> parsedHeaders, Method method){
        return parsedHeaders.get(method.name()).get(0);
    }

    private Map<String, String> mapPostBody(String postBody){
        return HttpRequestUtils.parseQueryString(postBody);
    }



}