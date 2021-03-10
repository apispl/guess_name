package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public interface FileFacade {

    boolean hasName(Path path, String name) throws IOException;
    byte[] fetchData(Path path) throws IOException;
}