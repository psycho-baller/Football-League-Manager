
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


// Junit setup for every function

/**
 * Junit 5 testing function
 * Simple method that attempts to read the text file you provided in the argument
 * If any issues happen it throws fileNotFound exception or IOexecption
 */
public class Tests {
    @Test
    @DisplayName("test for checkingBestPlayer")
    void checkingBestPlayer() {
        String filename = "data.csv";

        boolean containsImport = false;

        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while (line != null && !containsImport) {
                if (line.matches("import+\s.*")) {
                    containsImport = true;
                }
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }


}


