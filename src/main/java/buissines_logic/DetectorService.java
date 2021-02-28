package buissines_logic;

import java.io.IOException;

interface DetectorService {

    byte[] getAllTokens() throws IOException;
    String detectName(String name) throws IOException;
}
