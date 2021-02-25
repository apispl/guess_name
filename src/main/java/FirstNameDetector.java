import dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class FirstNameDetector extends NameDetector {

    public FirstNameDetector(FileFacade fileFacade, Path malePath, Path femalePath) {
        super(fileFacade, malePath, femalePath);
    }

    @Override
    public String detect(String nameSurname) throws IOException {
        String[] splited = nameSurname.split(" ");

        if (nameSurname.length() < 1)
            throw new NoSuchElementException("Add name");

        if (super.fileFacade.hasName(malePath, splited[0]))
            return "MALE";
        if (super.fileFacade.hasName(femalePath, splited[0]))
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }
}
