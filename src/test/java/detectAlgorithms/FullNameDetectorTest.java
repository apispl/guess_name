package detectAlgorithms;

import dataloader.FileFacade;
import dataloader.FileFacadeConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static ownassertions.Assertions.assertEquals;

public class FullNameDetectorTest {
    private static final Path MALE_TEST_PATH = Path.of("src/test/resources/male_names_test");
    private static final Path FEMALE_TEST_PATH = Path.of("src/test/resources/female_names_test");

    public static void main(String[] args) throws IOException {
        shouldDetectFullNameDetector_male();
        shouldDetectFullNameDetector_female();
        shouldDetectFullNameDetector_inconclusive();
        shouldNotDetectFullNameDetector_emptyInput();
        shouldNotDetectFullNameDetector_spaceInput();
    }

    static void shouldDetectFullNameDetector_male() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Karol Pompejusz Janusz Maria Jan";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("MALE", result);
    }

    static void shouldDetectFullNameDetector_female() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Karol Anna Jadwiga Anna";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("FEMALE", result);
    }

    static void shouldDetectFullNameDetector_inconclusive() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Anna Karol";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("INCONCLUSIVE", result);
    }

    static void shouldNotDetectFullNameDetector_emptyInput() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "";

        //when
        String errorMessage = "";
        try {
            nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);
        } catch (NoSuchElementException e) {
            errorMessage = e.getMessage();
        }

        //then
        assertEquals("Add name", errorMessage);
    }

    static void shouldNotDetectFullNameDetector_spaceInput() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = " ";

        //when
        String errorMessage = "";
        try {
            nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);
        } catch (NoSuchElementException e) {
            errorMessage = e.getMessage();
        }

        //then
        assertEquals("Add name", errorMessage);
    }
}
