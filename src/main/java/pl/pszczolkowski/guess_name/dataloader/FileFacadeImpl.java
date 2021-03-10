package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class FileFacadeImpl implements FileFacade {

    private final FileLineValidator fileLineValidator;
    private final FileFetcher fileFetcher;

    public FileFacadeImpl(FileLineValidator fileLineValidator, FileFetcher fileFetcher) {
        this.fileLineValidator = fileLineValidator;
        this.fileFetcher = fileFetcher;
    }

    @Override
    public boolean hasName(Path path, String name) throws IOException {
        return fileFetcher.fetch(path)
                .filter(fileLineValidator::validate)
                .anyMatch(line -> line.equals(name));
    }

    @Override
    public byte[] fetchData(Path path) throws IOException {
        return fileFetcher.fetch(path)
                .filter(fileLineValidator::validate)
                .map(str -> str.concat("\n"))
                .collect(Collectors.joining()).getBytes();
    }
}
