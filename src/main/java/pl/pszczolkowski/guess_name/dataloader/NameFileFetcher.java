package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class NameFileFetcher  implements  FileFetcher{
    @Override
    public Stream<String> fetch(Path path) throws IOException {
        return Files.lines(path);
    }

    @Override
    public InputStream transferData(Path path) throws FileNotFoundException {
        return new FileInputStream(new File(path.toString()));
    }
}
