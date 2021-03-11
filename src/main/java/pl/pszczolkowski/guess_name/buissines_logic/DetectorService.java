package pl.pszczolkowski.guess_name.buissines_logic;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface DetectorService {

    InputStreamResource getAllTokens() throws IOException;
    String detectName(String name) throws IOException;
}
