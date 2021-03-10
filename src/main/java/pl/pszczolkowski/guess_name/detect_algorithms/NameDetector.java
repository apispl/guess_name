package pl.pszczolkowski.guess_name.detect_algorithms;

import org.springframework.stereotype.Component;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;

@Component
public interface NameDetector {
    String detect(String nameSurname, FileFacade fileFacade, Path malePath, Path femalePath) throws IOException;

}
