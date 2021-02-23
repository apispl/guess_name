package dataloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class NameFileFether implements FileFetcher{

    @Override
    public Stream<String> fetch(Path path) throws IOException {
        return Files.lines(path);
    }
}
