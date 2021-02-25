import dataloader.FileFacade;
import dataloader.FileFacadeImpl;
import dataloader.FileFetcher;
import dataloader.FileLineValidator;
import dataloader.NameFileFether;
import dataloader.NameLineValidator;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    private static final Path MALE_PATH = Path.of("src/main/resources/male_names");
    private static final Path FEMALE_PATH = Path.of("src/main/resources/female_names");

    public static void main(String[] args) throws IOException {
        FileLineValidator fileLineValidator = new NameLineValidator();
        FileFetcher fileFetcher = new NameFileFether();
        FileFacade fileFacade = new FileFacadeImpl(fileLineValidator, fileFetcher);

        NameDetector firstNameDetector = new FirstNameDetector(fileFacade, MALE_PATH, FEMALE_PATH);
        NameDetector fullNameDetector = new FullNameDetector(fileFacade, MALE_PATH, FEMALE_PATH);

        System.out.println(firstNameDetector.detect("Karol"));
        System.out.println(fullNameDetector.detect("Jan Jan Anna Anna Anna"));

    }
}
