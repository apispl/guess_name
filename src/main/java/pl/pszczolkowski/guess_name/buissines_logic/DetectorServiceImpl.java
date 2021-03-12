package pl.pszczolkowski.guess_name.buissines_logic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;
import pl.pszczolkowski.guess_name.detect_algorithms.NameDetector;

import java.io.SequenceInputStream;

@Service
public class DetectorServiceImpl implements DetectorService {

    private final FileFacade fileFacade;
    private NameDetector nameDetector;

    @Autowired
    public DetectorServiceImpl(@Value("${app-info.detectAlgorithm}") String algorithmName, BeanFactory beanFactory, FileFacade fileFacade) {
        this.fileFacade = fileFacade;
        this.nameDetector = beanFactory.getBean(algorithmName, NameDetector.class);
    }

    @Override
    public InputStreamResource getAllTokens() {
        SequenceInputStream mergedStreams = fileFacade.fetchData();

        return new InputStreamResource(mergedStreams);
    }

    @Override
    public String detectName(String name) {
        return nameDetector.detect(name, fileFacade);
    }
}
