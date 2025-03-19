package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class HtmlReader {

    private static final String webAppUrls = "C:\\javas\\intellijProject\\web-application-server\\webapp";
    public static String readHtml(String urls){
        File file = new File(webAppUrls + urls);
        StringBuilder stringBuilder = new StringBuilder();
        try(InputStream in = Files.newInputStream(file.toPath())){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String index_html_line;
            while((index_html_line = bufferedReader.readLine()) !=null){
                stringBuilder.append(index_html_line);
            }
            String html = stringBuilder.toString();
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
