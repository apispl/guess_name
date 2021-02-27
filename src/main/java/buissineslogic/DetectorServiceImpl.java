package buissineslogic;

import dataloader.FileFacade;
import detectAlgorithms.NameDetector;

import java.io.IOException;
import java.nio.file.Path;

public class DetectorServiceImpl implements DetectorService {

    private final FileFacade fileFacade;
    private final NameDetector nameDetector;
    private final Path malePath;
    private final Path femalePath;

    public DetectorServiceImpl(FileFacade fileFacade, NameDetector nameDetector, Path malePath, Path femalePath) {
        this.fileFacade = fileFacade;
        this.nameDetector = nameDetector;
        this.malePath = malePath;
        this.femalePath = femalePath;
    }

    public byte[] getMaleTokens() throws IOException {
        return fileFacade.fetch(malePath);
    }

    public byte[] getFemaleTokens() throws IOException {
        return fileFacade.fetch(femalePath);
    }

    public String detectName(String name) throws IOException {
        return nameDetector.detect(name, fileFacade, malePath, femalePath);
    }
}
