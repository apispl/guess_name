package detect_algorithms;

import dataloader.FileFacade;
import dataloader.FileFacadeConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static ownassertions.Assertions.assertEquals;

class FirstNameDetectorTest {

    private static final Path MALE_TEST_PATH = Path.of("src/test/resources/male_names_test");
    private static final Path FEMALE_TEST_PATH = Path.of("src/test/resources/female_names_test");

    public static void main(String[] args) throws IOException {
        shouldDetectFirstNameDetector_male();
        shouldDetectFirstNameDetector_female();
        shouldDetectFirstNameDetector_inconclusive();
        shouldNotDetectFirstNameDetector_emptyInput();
        shouldNotDetectFirstNameDetector_spaceInput();
    }

    static void shouldDetectFirstNameDetector_male() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Karol Pompejusz";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("MALE", result);
    }

    static void shouldDetectFirstNameDetector_female() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Anna Jadwiga";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("FEMALE", result);
    }

    static void shouldDetectFirstNameDetector_inconclusive() throws IOException {
        //given
        NameDetector nameDetector = new FullNameDetector();
        FileFacade fileFacade = FileFacadeConfiguration.configure();
        String inputName = "Jadwiga";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("INCONCLUSIVE", result);
    }

    static void shouldNotDetectFirstNameDetector_emptyInput() throws IOException {
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

    static void shouldNotDetectFirstNameDetector_spaceInput() throws IOException {
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
