package pl.pszczolkowski.guess_name.detect_algorithms;

import org.springframework.stereotype.Component;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;

import java.util.NoSuchElementException;

@Component("fullName")
public class FullNameDetector implements NameDetector {

    @Override
    public String detect(String nameSurname, FileFacade fileFacade) {
        String[] splited = nameSurname.split(" ");
        int maleCounter = 0;
        int femaleCounter = 0;

        if (nameSurname.length() < 1 || splited.length < 1)
            throw new NoSuchElementException("Add name");

        for (String separatedName : splited) {
            if (fileFacade.hasMaleName(separatedName))
                maleCounter++;
            if (fileFacade.hasFemaleName(separatedName))
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
