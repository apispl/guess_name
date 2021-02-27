package dataloader;

import java.io.IOException;
import java.nio.file.Path;

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
    public byte[] fetch(Path path) throws IOException {
        StringBuilder builder = new StringBuilder();

        fileFetcher.fetch(path)
                .filter(fileLineValidator::validate)
                .forEach(string -> builder.append(string).append("\n"));

        return builder.toString().getBytes();
    }
}
