package dataloader;

import java.io.IOException;
import java.nio.file.Path;

public interface FileFacade {
    boolean hasName(Path path, String name) throws IOException;
}
