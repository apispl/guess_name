package detectAlgorithms;

import dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class FullNameDetector implements NameDetector {

    @Override
    public String detect(String nameSurname, FileFacade fileFacade, Path malePath, Path femalePath) throws IOException {
        String[] splited = nameSurname.split(" ");
        int maleCounter = 0;
        int femaleCounter = 0;

        if (nameSurname.length() < 1)
            throw new NoSuchElementException("Add name");

        for (String separatedName : splited) {
            if (fileFacade.hasName(malePath, separatedName))
                maleCounter++;
            if (fileFacade.hasName(femalePath, separatedName))
                femaleCounter++;
        }

        if (maleCounter > femaleCounter)
            return "MALE";
        if (maleCounter < femaleCounter)
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }
}
