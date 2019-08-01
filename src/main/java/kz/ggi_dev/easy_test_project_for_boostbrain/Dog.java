package kz.ggi_dev.easy_test_project_for_boostbrain;

public class Dog {
    private int id;
    private String name;
    private String breed;
    private String gender;
    private long dateOfBirth;
    private double weight;
    private String vaccinated;
    private long dateOfRegistration;
    private long dateOfLastInspection;

    public Dog() {
    }

    public Dog(int id, String name, String breed, String gender, long dateOfBirth, double weight, String vaccinated,
               long dateOfRegistration, long dateOfLastInspection
    ) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccinated = vaccinated;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfLastInspection = dateOfLastInspection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) { this.weight = weight; }

    public String getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }

    public long getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(long dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public long getDateOfLastInspection() {
        return dateOfLastInspection;
    }

    public void setDateOfLastInspection(long dateOfLastInspection) {
        this.dateOfLastInspection = dateOfLastInspection;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", weight=" + weight +
                ", vaccinated=" + vaccinated +
                ", dateOfRegistration=" + dateOfRegistration +
                ", dateOfLastInspection=" + dateOfLastInspection +
                '}';
    }
}
