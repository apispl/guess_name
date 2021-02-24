package dataloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public interface FileFacade {
    Set<String> extract(Path path) throws IOException;
}
