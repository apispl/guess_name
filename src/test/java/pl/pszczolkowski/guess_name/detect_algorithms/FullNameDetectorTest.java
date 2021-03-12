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

    private NameDetector nameDetector;
    private FileFacade fileFacade;

    @BeforeEach
    public void initEach(){
        nameDetector = new FullNameDetector();
        fileFacade = FileFacadeConfiguration.configure();
    }

    @Test
    void shouldDetectFullNameDetector_male() {
        //given
        String inputName = "Karol Pompejusz Janusz Maria Jan";

        //when
        String result = nameDetector.detect(inputName, fileFacade);

        //then
        assertEquals("MALE", result);
    }

    @Test
    void shouldDetectFullNameDetector_female() {
        //given
        String inputName = "Karol Anna Jadwiga Anna";

        //when
        String result = nameDetector.detect(inputName, fileFacade);

        //then
        assertEquals("FEMALE", result);
    }

    @Test
    void shouldDetectFullNameDetector_inconclusive() {
        //given
        String inputName = "Anna Karol";

        //when
        String result = nameDetector.detect(inputName, fileFacade);

        //then
        assertEquals("INCONCLUSIVE", result);
    }

    @Test
    void shouldNotDetectFullNameDetector_emptyInput() {
        //given
        String inputName = "";

        //when
        String errorMessage = "";
        try {
            nameDetector.detect(inputName, fileFacade);
        } catch (NoSuchElementException e) {
            errorMessage = e.getMessage();
        }

        //then
        assertTrue(errorMessage.contains("Add name"));
    }

    @Test
    void shouldNotDetectFullNameDetector_spaceInput() {
        //given
        String inputName = " ";

        //when
        String errorMessage = "";
        try {
            nameDetector.detect(inputName, fileFacade);
        } catch (NoSuchElementException e) {
            errorMessage = e.getMessage();
        }

        //then
        assertTrue(errorMessage.contains("Add name"));
    }
}
