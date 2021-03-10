package pl.pszczolkowski.guess_name.buissines_logic;

import java.io.IOException;

public interface DetectorService {

    byte[] getAllTokens() throws IOException;
    String detectName(String name) throws IOException;
}
