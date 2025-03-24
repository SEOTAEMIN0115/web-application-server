package servlet.request.header;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import servlet.request.header.enums.Method;

public class RequestHeader {
    private Method method;
    private List<String> cookie;

    private List<String> accept;

    private String connection;

    private String host;

    private Map<String, List<String>> headers;

    private String url;

    private Map<String, String> getBody;


    private Map<String, String> postBody;

    public RequestHeader(Method method, List<String> cookie, List<String> accept, String connection, String host,
        Map<String, List<String>> headers, String url, Map<String, String> getBody,
        Map<String, String> postBody) {
        this.method = method;
        this.cookie = cookie;
        this.accept = accept;
        this.connection = connection;
        this.host = host;
        this.headers = headers;
        this.url = url;
        this.getBody = getBody;
        this.postBody = postBody;
    }

    public Map<String, String> getGetBody() {
        return getBody;
    }

    public Map<String, String> getPostBody() {
        return postBody;





    }

    public Method getMethod() {
        return method;
    }

    public List<String> getCookie() {
        return cookie;
    }

    public List<String> getAccept() {
        return accept;
    }

    public String getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
            "method=" + method +
            ", cookie=" + cookie +
            ", accept=" + accept +
            ", connection='" + connection + '\'' +
            ", host='" + host + '\'' +
            ", headers=" + headers +
            ", url='" + url + '\'' +
            '}';
    }

    public String getUrl() {
        return url;
    }

    static public class Builder{
        private Method method;
        private List<String> cookie;

        private List<String> accept;

        private String connection;

        private String host;

        private String url;

        private Map<String, String> getBody;
        private Map<String, String> postBody;

        private Map<String, List<String>> headers;









        public Builder(){

        }
        public Builder setMethod(Method method){
            this.method = method;
            return this;
        }

        public Builder setCookie(List<String> cookie){
            this.cookie = cookie;
            return this;
        }

        public Builder setAccept(List<String> accept){
            this.accept = accept;
            return this;
        }
        //
        public Builder setConnection(String connection){
            this.connection = connection;
            return this;
        }
        public Builder setHost(String host){
            this.host = host;
            return this;
        }

        public Builder setHeaders(Map<String, List<String>> headers){
            this.headers = headers;
            return this;
        }

        public Builder setUrls(String url){
            this.url = url;
            if (url.contains("?")){
                int end_url = url.indexOf("?");
                this.url = url.substring(0, end_url);
                this.getBody = makeGetBody(url.substring(end_url + 1));
            }
            return this;
        }

        public Builder setPostBody(Map<String, String> postBody){
            this.postBody = postBody;
            return this;
        }

        public RequestHeader build(){
            return new RequestHeader(method, cookie, accept, connection, host, headers, url, getBody, postBody);
        }

        private Map<String, String> makeGetBody(String rawGetBody){
            Map<String, String> getBody = new HashMap<>();
            String[] getParameters = rawGetBody.split("&");
            for (String getParameter : getParameters) {
                String[] getParam = getParameter.split("=");
                getBody.put(getParam[0], getParam[1]);
            }
            return getBody;
        }
    }
}