package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

@Component
public class NameFileFetcher  implements  FileFetcher{
    @Override
    public Stream<String> fetch(String fileName) {
        InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
        return new BufferedReader(new InputStreamReader(inputStream)).lines();
    }

    @Override
    public InputStream transferData(String fileName) {
        return getClass().getResourceAsStream("/" + fileName);
    }
}
