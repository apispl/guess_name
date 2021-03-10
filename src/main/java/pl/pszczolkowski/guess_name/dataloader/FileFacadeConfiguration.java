package pl.pszczolkowski.guess_name.dataloader;

public class FileFacadeConfiguration {

    private FileFacadeConfiguration() {
        throw new IllegalStateException("Utility class");
    }

    public static FileFacade configure() {
        FileLineValidator fileLineValidator = new NameLineValidator();
        FileFetcher fileFetcher = new NameFileFetcher();
        return new FileFacadeImpl(fileLineValidator, fileFetcher);
    }
}
