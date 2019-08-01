package kz.ggi_dev.easy_test_project_for_boostbrain;

import java.io.File;

public class Main {
    private final String RESULT_FILE_PATH = "result.txt";

    public static void main(String[] args) {

        String inputFilePath = "";
        if(args.length > 0) {
            inputFilePath = args[0];
        } else {
            System.out.println("Wrong startup options!");
            System.exit(1);
        }

        File inputFile = new File(inputFilePath);

        if (!inputFile.exists()) {
            System.out.println("Input file does not exist!");
            System.exit(1);
        }

        DogManager dogManager = new DogManager();
        System.out.println("Импортирование данных...");
        dogManager.addDogsFromSCV(inputFile);
        File resultFile = new File("result.txt");
        System.out.println();
        System.out.println("Анализ данных и сохранение результатов в файл " + resultFile.getAbsolutePath());
        System.out.println();
        System.out.println(dogManager.saveAllAnalyzesToTextFile(resultFile));
        System.out.println("Готово!");

    }
}
