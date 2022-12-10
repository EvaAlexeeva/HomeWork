package Project.Program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Project.Exeption.StringEx;
import Project.Exeption.DataEx;
import Project.Console.ConsOn;

public class Program< C extends ConsOn> {

    private DataEx model;
    private C cons;

    public Program(C c) {
        cons = c;
    }

    public void start() {
        boolean wokring = true;
        do {
            String input = cons.getInput(
                    "Введите данные через пробел (Фамилию Имя Отчество ДатуРождения НомерТелефона Пол), или Exit для прекращения программы:");
            if (input.equals("Exit")) {
                wokring = false;
                break;
            } else {
                // множественные пробелы заменяем на одинарные (не считаем это страшной ошибкой)
                String[] splitedInput = input.replaceAll("\\s+", " ").split(" ");

                int inputDataCount = checkInputDataCount(splitedInput.length);
                if (inputDataCount == -1) {
                    cons.printOutput("Слишком мало данных на вводе (должно быть " + DataEx.dataCount
                            + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                } else if (inputDataCount == 0) {
                    cons.printOutput("Слишком много данных на вводе (должно быть " + DataEx.dataCount
                            + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                } else {
                    try {
                        model = new DataEx();
                        model.Data(splitedInput);
                        writePersonData(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (StringEx e) {
                        cons.printOutput(e.getMessage());
                    }
                }
            }
        } while (wokring);
    }

 
    private int checkInputDataCount(int inputDataCount) {
        if (inputDataCount < DataEx.dataCount) {
            return -1;
        } else if (inputDataCount > DataEx.dataCount) {
            return 0;
        } else {
            return inputDataCount;
        }
    }
    
    
    private void writePersonData(DataEx data) throws IOException {
        File filepath = new File(data.getLastName());
        try (FileWriter fw = new FileWriter(filepath, true)) {
            fw.append(data.toString() + "\n");
        } catch (IOException e) {
            throw e;
        }
    }
}

