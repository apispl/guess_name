import dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class FullNameDetector extends NameDetector {

    public FullNameDetector(FileFacade fileFacade, Path malePath, Path femalePath) {
        super(fileFacade, malePath, femalePath);
    }

    @Override
    public String detect(String nameSurname) throws IOException {
        String[] splited = nameSurname.split(" ");
        int maleCounter = 0;
        int femaleCounter = 0;

        if (nameSurname.length() < 1)
            throw new NoSuchElementException("Add name");

        for (String separatedName : splited) {
            if (isMale(separatedName))
                maleCounter++;
            if (isFemale(separatedName))
                femaleCounter++;
        }

        if (maleCounter > femaleCounter)
            return "MALE";
        if (maleCounter < femaleCounter)
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }

    private boolean isMale(String name) throws IOException {
        return super.fileFacade.hasName(malePath, name);
    }

    private boolean isFemale(String name) throws IOException {
        return super.fileFacade.hasName(femalePath, name);
    }
}
