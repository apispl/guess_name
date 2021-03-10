package pl.pszczolkowski.guess_name.detect_algorithms;

import org.springframework.stereotype.Component;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

@Component("firstName")
public class FirstNameDetector implements NameDetector {

    @Override
    public String detect(String nameSurname, FileFacade fileFacade, Path malePath, Path femalePath) throws IOException {
        String[] splited = nameSurname.split(" ");

        if (nameSurname.length() < 1 || splited.length < 1)
            throw new NoSuchElementException("Add name");

        if (fileFacade.hasName(malePath, splited[0]))
            return "MALE";
        if (fileFacade.hasName(femalePath, splited[0]))
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }
}
