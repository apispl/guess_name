package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

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
    public InputStream fetchData(Path path) throws IOException {
        return fileFetcher.transferData(path);
    }
}
