package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public interface FileFetcher {
    Stream<String> fetch(Path path) throws IOException;
}
