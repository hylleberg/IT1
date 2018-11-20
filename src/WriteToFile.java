import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
class WriteToFile {

    private Path file;

    /**
     * Responsible for writing to file.
     */
    WriteToFile(){
        this.file = Paths.get("log.txt");
        try {
            Files.write(file, Collections.singleton(""), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Responsible for writing to file.
     */
    WriteToFile(String fileName){
        this.file = Paths.get(fileName+".txt");
        try {
            Files.write(file, Collections.singleton(""), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param temp This is a representative for a temperature
     */
    void writeTemperatureToFile(double temp){
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(this.file.getFileName().toString(), true));
            out.write(temp + ",");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
