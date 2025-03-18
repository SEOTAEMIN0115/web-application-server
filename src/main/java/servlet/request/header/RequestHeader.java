package servlet.request.header;

import java.util.List;
import java.util.Map;

import servlet.request.header.enums.Method;

public class RequestHeader {
    private Method method;
    private List<String> cookie;

    private List<String> accept;

    private String connection;

    private String referer;

    private Map<String, List<String>> headers;

    public RequestHeader(Method method, List<String> cookie, List<String> accept, String connection, String referer,
        Map<String, List<String>> headers) {
        this.method = method;
        this.cookie = cookie;
        this.accept = accept;
        this.connection = connection;
        this.referer = referer;
        this.headers = headers;
    }

    static public class Builder {
        private Method method;
        private List<String> cookie;

        private List<String> accept;

        private String connection;

        private String referer;

        private Map<String, List<String>> headers;

        public Builder(RequestHeader requestHeader) {
            this.method = requestHeader.method;
            this.cookie = requestHeader.cookie;
            this.accept = requestHeader.accept;
            this.connection = requestHeader.connection;
            this.referer = requestHeader.referer;
            this.headers = requestHeader.headers;
        }

        public Builder setMethod(String method) {
            this.method = Method.valueOf(method);
            return this;
        }

        public Builder setCookie(List<String> cookie) {
            this.cookie = cookie;
            return this;
        }

        public Builder setAccept(List<String> accept) {
            this.accept = accept;
            return this;
        }

        //
        public Builder setConnection(String connection) {
            this.connection = connection;
            return this;
        }

        public Builder setReferer(String referer) {
            this.referer = referer;
            return this;
        }

        public Builder setHeaders(Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public RequestHeader build() {
            return new RequestHeader(method, cookie, accept, connection, referer, headers);
        }
    }
}