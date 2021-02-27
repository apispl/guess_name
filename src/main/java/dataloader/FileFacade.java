package dataloader;


import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileFacade {
    boolean hasName(Path path, String name) throws IOException;
    byte[] fetch(Path path) throws IOException;
}
