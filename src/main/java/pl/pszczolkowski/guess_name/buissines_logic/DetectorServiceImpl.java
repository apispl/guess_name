package pl.pszczolkowski.guess_name.buissines_logic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;
import pl.pszczolkowski.guess_name.detect_algorithms.NameDetector;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
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
    public InputStreamResource getAllTokens() throws IOException {
        InputStream maleInputStream = fileFacade.fetchData(MALE_PATH);
        InputStream femaleInputStream = fileFacade.fetchData(FEMALE_PATH);
        SequenceInputStream mergedStreams = new SequenceInputStream(maleInputStream, femaleInputStream);

        return new InputStreamResource(mergedStreams);
    }

    @Override
    public String detectName(String name) throws IOException {
        return nameDetector.detect(name, fileFacade, MALE_PATH, FEMALE_PATH);
    }
}
