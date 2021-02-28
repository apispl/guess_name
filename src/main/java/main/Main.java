package main;

import buissines_logic.DetectorServiceImpl;
import dataloader.FileFacade;
import dataloader.FileFacadeConfiguration;
import detect_algorithms.FirstNameDetector;
import detect_algorithms.FullNameDetector;
import detect_algorithms.NameDetector;
import http.NameApi;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final Path MALE_PATH = Path.of("src/main/resources/male_names");
    private static final Path FEMALE_PATH = Path.of("src/main/resources/female_names");

    public static void main(String[] args) throws IOException {
        FileFacade fileFacade = FileFacadeConfiguration.configure();

        NameDetector nameDetectorAlgorithm = appGui();

        DetectorServiceImpl detectorService = new DetectorServiceImpl(fileFacade, nameDetectorAlgorithm, MALE_PATH, FEMALE_PATH);

        NameApi nameApi = new NameApi(8080);
        nameApi.configure("/detector", detectorService);
        nameApi.start();
    }

    private static NameDetector appGui() {
        NameDetector nameDetectorAlgorithm;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your algorithm: \n " +
                "1 - FirstNameDetector (detect only by first word) \n " +
                "2 - FullNameDetector (detect by whole phrase) \n " +
                "type 1 or 2: ");

        int choose = scanner.nextInt();

        if (choose == 1)
            nameDetectorAlgorithm = new FirstNameDetector();
        else
            nameDetectorAlgorithm = new FullNameDetector();

        System.out.println("You choose " + choose + " algorithm.");
        return nameDetectorAlgorithm;
    }
}
