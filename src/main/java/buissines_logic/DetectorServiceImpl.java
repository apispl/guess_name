package buissines_logic;

import dataloader.FileFacade;
import detect_algorithms.NameDetector;

import java.io.IOException;
import java.nio.ByteBuffer;
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

    public byte[] getAllTokens() throws IOException {
        byte[] maleTokens = fileFacade.fetchData(malePath);
        byte[] femaleTokens = fileFacade.fetchData(femalePath);

        return concatByteArray(maleTokens, femaleTokens);
    }

    public String detectName(String name) throws IOException {
        return nameDetector.detect(name, fileFacade, malePath, femalePath);
    }

    private static byte[] concatByteArray(byte[] byte1, byte[] byte2) {
        return ByteBuffer.allocate(byte1.length + byte2.length)
                .put(byte1)
                .put(byte2)
                .array();
    }
}
