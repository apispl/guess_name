package buissineslogic;

import java.io.IOException;

public interface DetectorService {

    byte[] getMaleTokens() throws IOException;
    byte[] getFemaleTokens() throws IOException;
    String detectName(String name) throws IOException;
}
