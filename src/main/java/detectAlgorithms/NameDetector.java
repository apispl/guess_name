package detectAlgorithms;

import dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;

public interface NameDetector {

    String detect(String nameSurname, FileFacade fileFacade, Path malePath, Path femalePath) throws IOException;
}
