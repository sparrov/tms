package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogFileReaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFileReaderService.class);
    private final String PATH = "tms.log";
    private FileReader fileReader = null;
    private String line = "";

    public LogFileReaderService() throws FileNotFoundException {
    }

    public List<String> readLogFile() throws FileNotFoundException {
        try {
            fileReader = new FileReader(PATH);
        } catch (FileNotFoundException e) {
            LOGGER.error("Bląd otwarcia pliku!");
            System.exit(1);
        }
        BufferedReader bufferFileReader = new BufferedReader(fileReader);
        List<String> listOfReadLines = new ArrayList<>();
        try {
            while ((line = bufferFileReader.readLine()) != null) {
                if (line.contains("Service]"))
                    listOfReadLines.add(line);
            }
        } catch (IOException ioException) {
            LOGGER.error("Bląd odczytu pliku!");
            System.exit(2);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            LOGGER.error("Błąd zamkniecia pliku!");
            System.exit(3);
        }
        return listOfReadLines;
    }
}




