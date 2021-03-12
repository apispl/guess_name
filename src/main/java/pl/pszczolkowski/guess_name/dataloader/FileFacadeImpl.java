package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.SequenceInputStream;

@Component
class FileFacadeImpl implements FileFacade {

    private final FileLineValidator fileLineValidator;
    private final FileFetcher fileFetcher;

    public FileFacadeImpl(FileLineValidator fileLineValidator, FileFetcher fileFetcher) {
        this.fileLineValidator = fileLineValidator;
        this.fileFetcher = fileFetcher;
    }

    @Override
    public boolean hasMaleName(String name) {
        return fileFetcher.fetch("male_names")
                .filter(fileLineValidator::validate)
                .anyMatch(line -> line.equals(name));
    }

    @Override
    public boolean hasFemaleName(String name) {
        return fileFetcher.fetch("female_names")
                .filter(fileLineValidator::validate)
                .anyMatch(line -> line.equals(name));
    }

    @Override
    public SequenceInputStream fetchData() {
        InputStream maleInputStream = fileFetcher.transferData("male_names");
        InputStream femaleInputStream = fileFetcher.transferData("female_names");

        return new SequenceInputStream(maleInputStream, femaleInputStream);
    }
}
