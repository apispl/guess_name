import dataloader.FileFacade;

import java.io.IOException;
import java.nio.file.Path;

public abstract class NameDetector {

    public final FileFacade fileFacade;
    public final Path malePath;
    public final Path femalePath;

    public NameDetector(FileFacade fileFacade, Path malePath, Path femalePath) {
        this.fileFacade = fileFacade;
        this.malePath = malePath;
        this.femalePath = femalePath;
    }

    public abstract String detect(String nameSurname) throws IOException;
}
