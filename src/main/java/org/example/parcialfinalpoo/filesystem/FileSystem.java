package org.example.parcialfinalpoo.filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem {

    public boolean CreateFile(String fileName, String content) {
        FileWriter fw = null;

        if (fileName.charAt(0) == 'A' || fileName.charAt(0) == 'B' || fileName.charAt(0) == 'C' || fileName.charAt(0) == 'D') {
            File file = new File("Reportes/" + fileName);
            try {
                fw = new FileWriter(file);
                fw.write(content);
                fw.close();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

}
