package pl.pszczolkowski.guess_name.detect_algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pszczolkowski.guess_name.dataloader.FileFacade;
import pl.pszczolkowski.guess_name.dataloader.FileFacadeConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FullNameDetectorTest {

    private static final Path MALE_TEST_PATH = Path.of("src/test/resources/male_names_test");
    private static final Path FEMALE_TEST_PATH = Path.of("src/test/resources/female_names_test");

    private NameDetector nameDetector;
    private FileFacade fileFacade;

    @BeforeEach
    public void initEach(){
        nameDetector = new FullNameDetector();
        fileFacade = FileFacadeConfiguration.configure();
    }

    @Test
    void shouldDetectFullNameDetector_male() throws IOException {
        //given
        String inputName = "Karol Pompejusz Janusz Maria Jan";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("MALE", result);
    }

    @Test
    void shouldDetectFullNameDetector_female() throws IOException {
        //given
        String inputName = "Karol Anna Jadwiga Anna";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("FEMALE", result);
    }

    @Test
    void shouldDetectFullNameDetector_inconclusive() throws IOException {
        //given
        String inputName = "Anna Karol";

        //when
        String result = nameDetector.detect(inputName, fileFacade, MALE_TEST_PATH, FEMALE_TEST_PATH);

        //then
        assertEquals("INCONCLUSIVE", result);
    }

    @Test
    void shouldNotDetectFullNameDetector_emptyInput() throws IOException {
        //given
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

    @Test
    void shouldNotDetectFullNameDetector_spaceInput() throws IOException {
        //given
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
