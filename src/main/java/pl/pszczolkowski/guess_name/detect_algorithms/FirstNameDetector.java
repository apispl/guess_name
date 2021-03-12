package pl.pszczolkowski.guess_name.detect_algorithms;

import org.springframework.stereotype.Component;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;

import java.util.NoSuchElementException;

@Component("firstName")
public class FirstNameDetector implements NameDetector {

    @Override
    public String detect(String nameSurname, FileFacade fileFacade) {
        String[] splited = nameSurname.split(" ");

        if (nameSurname.length() < 1 || splited.length < 1)
            throw new NoSuchElementException("Add name");

        if (fileFacade.hasMaleName(splited[0]))
            return "MALE";
        if (fileFacade.hasFemaleName(splited[0]))
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }
}
