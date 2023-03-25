import utilities.DataManager;

import java.io.*;

/**
 * Class to run Application
 */
public class Application {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        try {
            FileReader fileReader = new FileReader("input");
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                dataManager.readData(line);
            }
            PrintStream stdout = System.out;

            System.out.println("Программа успешно считала данные");

            dataManager.generateReports();

            System.setOut(stdout);

            System.out.println("Программа успешно сгенерировала отчёты в папку reports");

            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Указанный входной/выходной файл не найден");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при считывании файла");
        }
    }
}
