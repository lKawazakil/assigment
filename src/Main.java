import java.io.*;
import java.util.*;

class Person {
    String name;
    String surname;
    int age;
    boolean gender;
    public Person(String name, String surname, int age, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }
    public String toString() {
        return "Hi, I am " + name + " " + surname + ", a " + age + "-year-old " + (gender ? "Male" : "Female") + ".";
    }
}
class Student extends Person {
    int studentID;
    List<Integer> grades = new ArrayList<>();
    public Student(String name, String surname, int age, boolean gender) {
        super(name, surname, age, gender);
        this.studentID = new Random().nextInt(10000); // Генерация случайного ID студента
    }
    public void addGrade(int grade) {
        grades.add(grade);
    }
    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / (double) grades.size();
    }
    @Override
    public String toString() {
        return super.toString() + " I am a student with ID " + studentID + ".";
    }
}
class Teacher extends Person {
    String subject;
    int yearsOfExperience;
    int salary;
    public Teacher(String name, String surname, int age, boolean gender, String subject, int yearsOfExperience, int salary) {
        super(name, surname, age, gender);
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }
    public void giveRaise(double percentage) {
        salary += salary * (percentage / 100);
    }
    @Override
    public String toString() {
        return super.toString() + " I teach " + subject + ".";
    }
}
class School {
    List<Person> members = new ArrayList<>();
    public void addMember(Person person) {
        members.add(person);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Person member : members) {
            sb.append(member.toString()).append("\n");
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        List<Student> students = loadStudentsFromFile("C:\\Users\\User\\IdeaProjects\\untitled\\out\\production\\untitled\\students.txt");
        List<Teacher> teachers = loadTeachersFromFile("C:\\Users\\User\\IdeaProjects\\untitled\\out\\production\\untitled\\teachers.txt");
        School school = new School();
        for (Student student : students) {
            school.addMember(student);
        }
        for (Teacher teacher : teachers) {
            school.addMember(teacher);
        }
        System.out.println(school.toString());
        for (Student student : students) {
            System.out.println(student.name + "'s GPA: " + student.calculateGPA());
        }
        for (Teacher teacher : teachers) {
            if (teacher.yearsOfExperience > 10) {
                teacher.giveRaise(10);
                System.out.println(teacher.name + "'s new salary after raise: " + teacher.salary);
            }
        }
    }
    public static List<Student> loadStudentsFromFile(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String name = parts[0];
            String surname = parts[1];
            int age = Integer.parseInt(parts[2]);
            boolean gender = parts[3].equalsIgnoreCase("Male");
            Student student = new Student(name, surname, age, gender);
            for (int i = 4; i < parts.length; i++) {
                student.addGrade(Integer.parseInt(parts[i]));
            }
            students.add(student);
        }
    reader.close();
        return students;
    }

    public static List<Teacher> loadTeachersFromFile(String filename) throws IOException {
        List<Teacher> teachers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String name = parts[0];
            String surname = parts[1];
            int age = Integer.parseInt(parts[2]);
            boolean gender = parts[3].equalsIgnoreCase("Male");
            String subject = parts[4];
            int yearsOfExperience = Integer.parseInt(parts[5]);
            int salary = Integer.parseInt(parts[6]);
            Teacher teacher = new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
            teachers.add(teacher);
        }
        reader.close();
        return teachers;
    }
}
