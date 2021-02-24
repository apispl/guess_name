package dataloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileFacadeImpl implements FileFacade {

    private final FileLineValidator fileLineValidator;
    private final FileFetcher fileFetcher;

    public FileFacadeImpl(FileLineValidator fileLineValidator, FileFetcher fileFetcher) {
        this.fileLineValidator = fileLineValidator;
        this.fileFetcher = fileFetcher;
    }

    @Override
    public Set<String> extract(Path path) throws IOException {
        return fileFetcher.fetch(path)
                .filter(fileLineValidator::validate)
                .collect(Collectors.toSet());
    }
}
