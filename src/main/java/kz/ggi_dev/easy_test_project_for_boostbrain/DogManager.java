package kz.ggi_dev.easy_test_project_for_boostbrain;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DogManager {



    private final String GENDER_MALE = "самец";
    private final String GENDER_FEMALE = "самка";

    private final String VACCINANED_TRUE = "да";
    private final String VACCINANED_FALSE = "нет";

    private List<Dog> dogList;
    private List<InvalidLine> invalidLines;

    public DogManager() {
        this.dogList = new ArrayList<Dog>();
        this.invalidLines = new ArrayList<>();
    }

    public boolean addDogsFromSCV(final File file) {
        boolean result = false;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    Dog dog = new Dog();
                    dog.setId(Integer.parseInt(values[0]));
                    dog.setName(values[1]);
                    dog.setBreed(values[2]);
                    dog.setGender(values[3]);
                    dog.setDateOfBirth(new Date(values[4]).getTime());
                    dog.setWeight(Double.parseDouble(values[5]));
                    dog.setVaccinated(values[6]);
                    dog.setDateOfRegistration(new Date(values[7]).getTime());
                    dog.setDateOfLastInspection(new Date(values[8]).getTime());
                    dogList.add(dog);
                } catch (Exception e) {
                    invalidLines.add(new InvalidLine(line, e));
                }
            }
            bf.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String saveAllAnalyzesToTextFile(final File file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Гендерный анализ:").append("\n");
        stringBuilder.append(this.genderAnalysis()).append("\n\n");
        stringBuilder.append("Анализ вакцинации:").append("\n");
        stringBuilder.append(this.vaccinatedAnalysis()).append("\n\n");
        stringBuilder.append("Количество собак прошедших осмотр больше года назад = "
                + this.getCountDogsThatWereNotOnInspection(365)).append("\n\n");
        stringBuilder.append("Количество импортированных записей  = "
                + this.dogList.size()).append("\n\n");
        stringBuilder.append("Количество записей непрошедших импортирование = "
                + this.invalidLines.size()).append("\n\n");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String genderAnalysis() {
        StringBuilder result = new StringBuilder();
        int countMale = 0;
        int countFemale = 0;
        int countUndefinedGender = 0;
        for (Dog dog: dogList) {
            if (GENDER_MALE.equals(dog.getGender())) countMale++;
            if (GENDER_FEMALE.equals(dog.getGender())) countFemale++;
        }
        countUndefinedGender = dogList.size() - countMale - countFemale;

        if (countUndefinedGender == 0) {
            result.append(GENDER_MALE + "/" + GENDER_FEMALE + " = ");
            result.append(countMale + "/" + countFemale + " = ");
            double perctMale = countMale / (dogList.size() * 0.01);
            double perctFemale = 100 - perctMale;
            result.append(String.format("(%.2f%%%%/%.2f%%)",perctMale,perctFemale));
        } else {
            result.append(GENDER_MALE + "/" + GENDER_FEMALE +  "/ошибка = ");
            result.append(countMale + "/" + countFemale + "/" + countUndefinedGender + " = ");
            double perctMale = countMale / (dogList.size() * 0.01);
            double perctFemale = countFemale / (dogList.size() * 0.01);
            double perctUndefined = 100 - perctMale - perctFemale;
            result.append(String.format("(%.2f%%/%.2f%%/%.2f%%)",perctMale,perctFemale, perctUndefined));

        }
        return result.toString();
    }

    public String vaccinatedAnalysis() {
        StringBuilder result = new StringBuilder();
        int countVac = 0;
        int countUnvac = 0;
        int countUndefinedVac = 0;
        for (Dog dog: dogList) {
            if (VACCINANED_TRUE.equals(dog.getVaccinated())) countVac++;
            if (VACCINANED_FALSE.equals(dog.getVaccinated())) countUnvac++;
        }
        countUndefinedVac = dogList.size() - countVac - countUnvac;

        if (countUndefinedVac == 0) {
            result.append(VACCINANED_TRUE + "/" + VACCINANED_FALSE + " = ");
            result.append(countVac + "/" + countUnvac + " = ");
            double perctVac = countVac / (dogList.size() * 0.01);
            double perctUnvac = 100 - perctVac;
            result.append(String.format("(%.2f%%%%/%.2f%%)",perctVac,perctUnvac));
        } else {
            result.append(VACCINANED_TRUE + "/" + VACCINANED_FALSE + "/ошибка = ");
            result.append(countVac + "/" + countUnvac + "/" + countUndefinedVac + " = ");
            double perctVac = countVac / (dogList.size() * 0.01);
            double perctUnvac = countUnvac / (dogList.size() * 0.01);
            double perctUndefinedVac = 100 - perctVac - perctUnvac;
            result.append(String.format("(%.2f%%/%.2f%%/%.2f%%)",perctVac,perctUnvac,perctUndefinedVac));
        }
        return result.toString();
    }

    public int getCountDogsThatWereNotOnInspection(final int days) {
        int result = 0;
        long time = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000);
        for (Dog dog: dogList) {
            if (dog.getDateOfLastInspection() <= time) result++;
        }
        return result;
    }


    public String printDogs(final int limit) {
        StringBuilder result = new StringBuilder();

        if (limit < 1) {
            for (Dog dog: dogList) result.append(dog).append("\n");
        }
        if (dogList.size() <= limit) {
            for (int i = 0; i < dogList.size(); i++) result.append(dogList.get(i)).append("\n");
        }
        if (dogList.size() > limit) {
            for (int i = 0; i < limit; i++) result.append(dogList.get(i)).append("\n");
        }
        return result.toString();
    }

    public String printInvalidLines() {
        StringBuilder result = new StringBuilder();
        for (InvalidLine line: invalidLines) {
            result.append(line.getLine() + " " + line.getE().toString()).append("\n");
        }

        return result.toString();
    }

    static class InvalidLine {
        private String line;
        private Exception e;

        public InvalidLine(String line, Exception e) {
            this.line = line;
            this.e = e;
        }

        public String getLine() {
            return line;
        }

        public Exception getE() {
            return e;
        }
    }
}


