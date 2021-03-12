package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.stream.Stream;

@Component
public interface FileFetcher {
    Stream<String> fetch(String fileName);
    InputStream transferData(String fileName);
}
