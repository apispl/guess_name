package dataloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileFacade {
    List<String> extract(Path path) throws IOException;
}
