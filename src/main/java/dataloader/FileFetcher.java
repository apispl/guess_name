package dataloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

interface FileFetcher {

    Stream<String> fetch(Path path) throws IOException;
}
