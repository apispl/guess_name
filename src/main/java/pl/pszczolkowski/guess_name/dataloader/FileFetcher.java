package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public interface FileFetcher {
    Stream<String> fetch(Path path) throws IOException;
    InputStream transferData(Path path) throws FileNotFoundException;
}
