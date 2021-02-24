import dataloader.FileFacade;
import dataloader.FileFacadeImpl;
import dataloader.FileFetcher;
import dataloader.FileLineValidator;
import dataloader.NameFileFether;
import dataloader.NameLineValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        FileLineValidator fileLineValidator = new NameLineValidator();
        FileFetcher fileFetcher = new NameFileFether();
        FileFacade fileFacade = new FileFacadeImpl(fileLineValidator, fileFetcher);

        Set<String> maleNames = fileFacade.extract(Path.of("src/main/resources/male_names"));
        Set<String> femaleNames = fileFacade.extract(Path.of("src/main/resources/female_names"));
        maleNames.forEach(System.out::println);
        femaleNames.forEach(System.out::println);
        System.out.println("------------------");

        NameDetector firstNameDetector = new FirstNameDetector(maleNames, femaleNames);
        System.out.println(firstNameDetector.detect("Agata"));
        NameDetector fullNameDetector = new FullNameDetector(maleNames, femaleNames);
        System.out.println(fullNameDetector.detect("Jan Maria Rokita"));
    }
}
