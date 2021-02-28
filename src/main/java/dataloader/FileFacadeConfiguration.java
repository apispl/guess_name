package dataloader;

public class FileFacadeConfiguration {

    private FileFacadeConfiguration() {
        throw new IllegalStateException("Utility class");
    }

    public static FileFacade configure() {
        FileLineValidator fileLineValidator = new NameLineValidator();
        FileFetcher fileFetcher = new NameFileFether();
        return new FileFacadeImpl(fileLineValidator, fileFetcher);
    }
}
