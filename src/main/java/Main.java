import com.sun.net.httpserver.HttpServer;
import dataloader.FileFacade;
import dataloader.FileFacadeConfiguration;
import detectAlgorithms.FirstNameDetector;
import detectAlgorithms.FullNameDetector;
import detectAlgorithms.NameDetector;
import buissineslogic.DetectorServiceImpl;
import http.NameApi;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    private static final Path MALE_PATH = Path.of("src/main/resources/male_names");
    private static final Path FEMALE_PATH = Path.of("src/main/resources/female_names");

    public static void main(String[] args) throws IOException {
        FileFacade fileFacade = FileFacadeConfiguration.configure();

        NameDetector firstNameDetector = new FirstNameDetector();
        NameDetector fullNameDetector = new FullNameDetector();

        DetectorServiceImpl detectorService = new DetectorServiceImpl(fileFacade, fullNameDetector, MALE_PATH, FEMALE_PATH);

        HttpServer nameApi = NameApi.create(8080);
        NameApi.configure(nameApi, "/detector", detectorService);
        nameApi.start();
    }
}
