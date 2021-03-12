package pl.pszczolkowski.guess_name.detect_algorithms;

import org.springframework.stereotype.Component;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;

@Component
public interface NameDetector {
    String detect(String nameSurname, FileFacade fileFacade);
}
