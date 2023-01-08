
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private final String fileDir;
    private List<String> content;
    private URL endpoint;

    public FileReader(String fileName) {
        this.fileDir = fileName;
        content = new ArrayList<>();
    }

    public void read() throws IOException {
        Files.lines(Paths.get(fileDir))
                .forEach(line -> {
                    if (!line.equals("")) content.add(line);
                });

        try {
            endpoint = new URL(content.get(0));
            content.remove(0);
        } catch (MalformedURLException e) {
            //а-ля валидация эндпоинта
        }
    }

    public String getEndpointName() {
        return endpoint != null ? endpoint.toString() : "";
    }

    public List<String> getContent() {
        return content;
    }
}
