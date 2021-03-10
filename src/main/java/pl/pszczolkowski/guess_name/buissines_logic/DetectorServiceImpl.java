package pl.pszczolkowski.guess_name.buissines_logic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;
import pl.pszczolkowski.guess_name.detect_algorithms.NameDetector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

@Service
public class DetectorServiceImpl implements DetectorService {

    private static final Path MALE_PATH = Path.of("src/main/resources/male_names");
    private static final Path FEMALE_PATH = Path.of("src/main/resources/female_names");

    private final FileFacade fileFacade;
    private NameDetector nameDetector;

    @Autowired
    public DetectorServiceImpl(@Value("${app-info.detectAlgorithm}") String algorithmName, BeanFactory beanFactory, FileFacade fileFacade) {
        this.fileFacade = fileFacade;
        this.nameDetector = beanFactory.getBean(algorithmName, NameDetector.class);
    }

    @Override
    public byte[] getAllTokens() throws IOException {
        byte[] maleTokens = fileFacade.fetchData(MALE_PATH);
        byte[] femaleTokens = fileFacade.fetchData(FEMALE_PATH);

        return concatByteArray(maleTokens, femaleTokens);
    }

    @Override
    public String detectName(String name) throws IOException {
        return nameDetector.detect(name, fileFacade, MALE_PATH, FEMALE_PATH);
    }

    private static byte[] concatByteArray(byte[] byte1, byte[] byte2) {
        return ByteBuffer.allocate(byte1.length + byte2.length)
                .put(byte1)
                .put(byte2)
                .array();
    }
}
