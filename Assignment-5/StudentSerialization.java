import java.io.*;

public class StudentSerialization {
    public static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private int id;
        private String name;
        private double gpa;

        public Student(int id, String name, double gpa) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        // Getters
        public int getId() { return id; }
        public String getName() { return name; }
        public double getGpa() { return gpa; }
    }

    public static void main(String[] args) {
        // Create a Student object
        Student student = new Student(101, "John Doe", 3.8);
        String filename = "student.ser";

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(student);
            System.out.println("Student details saved successfully!");

        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("\nReading from file...");
            System.out.println("Student ID: " + deserializedStudent.getId());
            System.out.println("Student Name: " + deserializedStudent.getName());
            System.out.println("Student GPA: " + deserializedStudent.getGpa());

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
