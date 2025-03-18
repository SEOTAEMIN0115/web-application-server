package servlet;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface Parser {
    public Map<String, List<String>> parse(InputStream in);
}
