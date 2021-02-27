package dataloader;

public class FileFacadeConfiguration {

    public static FileFacade configure() {
        FileLineValidator fileLineValidator = new NameLineValidator();
        FileFetcher fileFetcher = new NameFileFether();
        return new FileFacadeImpl(fileLineValidator, fileFetcher);
    }
}
