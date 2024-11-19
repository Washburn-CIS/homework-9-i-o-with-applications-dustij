public class Student {
    String firstName, lastName;
    double grade;

    public Student(String firstName, String lastName, double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double newGrade) {
        grade = newGrade;
    }

    public static int getExamAverage(int ... grades) {
        // find the lowest grade
        int lowest = Integer.MAX_VALUE;
        int lowestIndex = -1;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i];
                lowestIndex = i;
            }
        }

        // sum grades except lowest one
        double total = 0;
        for (int i = 0; i < grades.length; i++) {
            if (i == lowestIndex) continue;
            total += grades[i];
        }

        // round up
        return (int) ((total / (grades.length - 1)) + 0.5);
    }
}
