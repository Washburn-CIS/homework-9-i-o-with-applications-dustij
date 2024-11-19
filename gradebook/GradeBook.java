import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeBook {
    private static Student[] students;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // TODO: initialize students from contents of grades.txt file
        try (BufferedReader br = new BufferedReader(new FileReader("grades.txt"))) {
            int numberOfStudentsInFile = Integer.parseInt(br.readLine());
            students = new Student[numberOfStudentsInFile];
            
            String studentData;
            int i = 0;
            while ((studentData = br.readLine()) != null) {
                String[] data = studentData.split(",");
                
                String firstName = data[0];
                String lastName = data[1];
                try {
                    double grade = Double.parseDouble(data[2]);
                    students[i] = new Student(firstName, lastName, grade);
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing student grade from 'grades.txt' -> " + firstName + " " + lastName);
                    System.exit(1);
                }

            }
        } catch (IOException e) {
            System.out.println("An error occured while trying to read file 'grades.txt'");
            System.exit(1);
        }

        System.out.println("Welcome to the CM111 Grade Book App!");

        while(true) {
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1) List Class Grades");
            System.out.println("2) Update Grade");
            System.out.println("3) Calculate Exam Average");
            System.out.println("4) Exit");
            System.out.print("\nPlease choose an option: ");
            String choice = input.nextLine();
            System.out.println();
            switch(choice) {
                case "1": 
                    for(Student student: students) {
                        System.out.printf("%s, %s: %f%n", student.getLastName(), 
                                                        student.getFirstName(), 
                                                        student.getGrade());
                    }
                    break;
                case "2":
                    System.out.println("Enter First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lname = input.nextLine();
                    
                    for(Student student: students) {
                        if(student.getFirstName().equals(fname) &&
                           student.getLastName().equals(lname)) {
                           System.out.println("Enter Grade: ");
                           student.setGrade(Double.parseDouble(input.nextLine()));
                           System.out.println("Grade updated");
                           continue;
                        }
                    }
                    System.out.println("Student not found");
                    break;
                case "3":
                    System.out.print("Enter grades separated by spaces: ");
                    String[] strGrades = input.nextLine().split(" ");
                    int[] intGrades = new int[strGrades.length];
                    for (int i = 0; i < intGrades.length; i++) {
                        intGrades[i] = Integer.parseInt(strGrades[i]);
                    }
                    int avgGrade = Student.getExamAverage(intGrades);
                    System.out.println("Exam average grade is: " + avgGrade);
                    break;
                case "4":
                    // Challenge: write code to save the grades to grades.txt
                    try (PrintWriter writer = new PrintWriter(new File("grades.txt"))) {
                        writer.println(students.length);
                        for (Student student : students) {
                            writer.printf("%s,%s,%.0f%n", student.getFirstName(), student.getLastName(), student.getGrade());
                        }
                    } catch (IOException e) {
                        System.out.println("Failed to save updates to 'grades.txt'");
                    }
                    System.out.println("Goodbye!");
                    return;

            }
        }
    }
}
