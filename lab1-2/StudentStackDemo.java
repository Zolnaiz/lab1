import java.util.Scanner;
import java.util.ArrayList;

// Student class
class Student {
    String code;
    String name;
    int age;
    double gpa;
    String gender;

    // Constructor
    public Student(String code, String name, int age, double gpa, String gender) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.gender = gender;
    }

    // Print student information
    public void printStudent() {
        System.out.println("Код: " + code + ", Нэр: " + name + ", Нас: " + age + ", Голч: " + gpa + ", Хүйс: " + gender);
    }

    // Getter methods
    public String getCode() {
        return code;
    }

    public double getGpa() {
        return gpa;
    }
}

// StudentStack class
class StudentStack {
    private ArrayList<Student> stackList;

    // Constructor
    public StudentStack() {
        stackList = new ArrayList<>();
    }

    // Check if stack is full
    public boolean isFull(int capacity) {
        return stackList.size() >= capacity;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    // Add a student (Push)
    public void push(Student student) {
        stackList.add(student);
        System.out.println("Сурагч амжилттай нэмэгдлээ: " + student.name);
    }

    // Remove a student (Pop)
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Жагсаалт хоосон байна.");
            return null;
        }
        return stackList.remove(stackList.size() - 1);
    }

    // Get the size of the stack
    public int size() {
        return stackList.size();
    }

    // Print the stack
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Жагсаалт хоосон байна.");
        } else {
            System.out.println("Жагсаалтад байгаа сурагчид:");
            for (int i = stackList.size() - 1; i >= 0; i--) {
                stackList.get(i).printStudent();
            }
        }
    }

    // Remove a student by code
    public boolean removeStudentByCode(String code) {
        for (int i = 0; i < stackList.size(); i++) {
            if (stackList.get(i).getCode().equals(code)) {
                stackList.remove(i);
                return true;
            }
        }
        return false;
    }

    // Split students into good and bad based on GPA
    public void splitStack(StudentStack goodStudents, StudentStack badStudents, double gpaThreshold) {
        for (Student student : new ArrayList<>(stackList)) {
            if (student.getGpa() >= gpaThreshold) {
                goodStudents.push(student);
            } else {
                badStudents.push(student);
            }
        }
        stackList.clear(); // Clear original stack after splitting
        System.out.println("Сурагчдыг хуваасан: " + goodStudents.size() + " сайн, " + badStudents.size() + " муу.");
    }

    // Print good students
    public void printGoodStudents() {
        if (isEmpty()) {
            System.out.println("Сайн сурагчид олдсонгүй.");
        } else {
            System.out.println("Сайн сурагчид:");
            for (Student student : stackList) {
                if (student.getGpa() >= 2.0) { // Assuming GPA >= 2.0 is "good"
                    student.printStudent();
                }
            }
        }
    }

    // Print bad students
    public void printBadStudents() {
        if (isEmpty()) {
            System.out.println("Муу сурагчид олдсонгүй.");
        } else {
            System.out.println("Муу сурагчид:");
            for (Student student : stackList) {
                if (student.getGpa() < 2.0) { // Assuming GPA < 2.0 is "bad"
                    student.printStudent();
                }
            }
        }
    }

    // Print students by gender
    public void printStudentsByGender(String gender) {
        boolean found = false;
        for (Student student : stackList) {
            if (student.gender.equals(gender)) {
                student.printStudent();
                found = true;
            }
        }
        if (!found) {
            System.out.println(gender + " хүйстэй сурагчид олдсонгүй.");
        }
    }
}

// Main class
public class StudentStackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the total number of students
        System.out.print("Сурагчдын нийт тоог оруулна уу?: ");
        int capacity = getValidCapacity(scanner);

        StudentStack studentStack = new StudentStack();
        StudentStack goodStudents = new StudentStack();
        StudentStack badStudents = new StudentStack();

        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            handleMenuChoice(choice, studentStack, goodStudents, badStudents, capacity, scanner);
            showRemainingCapacity(studentStack, capacity); // Display remaining capacity after each action
        }
    }

    // Get a valid capacity from user
    private static int getValidCapacity(Scanner scanner) {
        int capacity;
        while (true) {
            try {
                capacity = Integer.parseInt(scanner.nextLine().trim());
                if (capacity <= 0) {
                    System.out.print("Жагсаалтын хэмжээ эерэг тоо байх ёстой. Дахин оруулна уу?: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Жагсаалтын хэмжээ зөвхөн тоогоор оруулна уу?: ");
            }
        }
        return capacity;
    }

    // Show the menu options
    private static void showMenu() {
        System.out.println("\n1. Сурагч оруулах");
        System.out.println("2. Сурагч хасах");
        System.out.println("3. Жагсаалтыг харах");
        System.out.println("4. Сурагчдын тоог харах");
        System.out.println("5. Гарах");
        System.out.println("6. Устгах сурагчийн кодыг оруулна уу");
        System.out.println("7. Сурагчдыг голчоор нь хуваах");
        System.out.println("8. Жагсаалтыг нэгтгэх");
        System.out.println("9. Стект үлдсэн зайг харах");
        System.out.println("10. Хүйсээр сурагчдыг харах");
        System.out.print("Үйлдэлээ сонгоно уу: ");
    }

    // Handle menu choices
    private static void handleMenuChoice(String choice, StudentStack studentStack, StudentStack goodStudents,
                                          StudentStack badStudents, int capacity, Scanner scanner) {
        switch (choice) {
            case "1":
                addStudent(studentStack, capacity, scanner);
                break;
            case "2":
                removeStudent(studentStack);
                break;
            case "3":
                studentStack.printStack();
                break;
            case "4":
                showStudentCount(studentStack, capacity);
                break;
            case "5":
                exitProgram(scanner);
                break;
            case "6":
                removeStudentByCode(studentStack, scanner);
                break;
            case "7":
                splitStudentsByGPA(studentStack, goodStudents, badStudents, scanner);
                break;
            case "8":
                mergeStudentStacks(studentStack, goodStudents, badStudents);
                break;
            case "9":
                showRemainingCapacity(studentStack, capacity);
                break;
            case "10":
                showStudentsByGender(studentStack, scanner);
                break;
            default:
                System.out.println("Алдаатай сонголт, дахин оролдоно уу.");
                break;
        }
    }

    // Add a student
    private static void addStudent(StudentStack studentStack, int capacity, Scanner scanner) {
        if (studentStack.isFull(capacity)) {
            System.out.println("Жагсаалт дүүрсэн байна. Сурагч нэмэх боломжгүй.");
            return;
        }

        System.out.print("Сурагчийн кодыг оруулна уу?: ");
        String code = scanner.nextLine().trim();
        System.out.print("Сурагчийн нэрийг оруулна уу?: ");
        String name = scanner.nextLine().trim();
        int age = getValidAge(scanner);
        double gpa = getValidGPA(scanner);
        String gender = getValidGender(scanner);

        Student student = new Student(code, name, age, gpa, gender);
        studentStack.push(student);
    }

    // Get a valid age
    private static int getValidAge(Scanner scanner) {
        int age;
        while (true) {
            System.out.print("Сурагчийн нас: ");
            String ageInput = scanner.nextLine().trim();
            try {
                age = Integer.parseInt(ageInput);
                if (age < 0 || age > 100) {
                    System.out.println("Нас 0-с 100 хүртэлх тоо байх ёстой.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Зөвхөн тоо оруулна уу.");
            }
        }
        return age;
    }

    // Get a valid GPA
    private static double getValidGPA(Scanner scanner) {
        double gpa;
        while (true) {
            System.out.print("Сурагчийн голч (0.0 - 4.0): ");
            String gpaInput = scanner.nextLine().trim();
            try {
                gpa = Double.parseDouble(gpaInput);
                if (gpa < 0.0 || gpa > 4.0) {
                    System.out.println("Голч 0.0-с 4.0 хүртэлх утга байх ёстой.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Зөвхөн тоо оруулна уу.");
            }
        }
        return gpa;
    }

    // Get a valid gender
    private static String getValidGender(Scanner scanner) {
        String gender;
        while (true) {
            System.out.print("Сурагчийн хүйс (1: Эрэгтэй, 2: Эмэгтэй): ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                gender = "Эрэгтэй";
                break;
            } else if (input.equals("2")) {
                gender = "Эмэгтэй";
                break;
            } else {
                System.out.println("Хүйс зөвхөн '1' эсвэл '2' байх ёстой. Дахин оруулна уу.");
            }
        }
        return gender;
    }

    // Remove a student
    private static void removeStudent(StudentStack studentStack) {
        Student removedStudent = studentStack.pop();
        if (removedStudent != null) {
            System.out.println("Устгасан сурагч: " + removedStudent.name);
        }
    }

    // Show student count
    private static void showStudentCount(StudentStack studentStack, int capacity) {
        int count = studentStack.size();
        System.out.println("Одоогоор " + count + " сурагч байна. Жагсаалтын хүчин чадал: " + capacity);
    }

    // Exit the program
    private static void exitProgram(Scanner scanner) {
        System.out.print("Програмыг хаахдаа итгэлтэй байна уу? (y/n): ");
        String confirmation = scanner.nextLine().trim();
        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println("Програм хаагдлаа.");
            System.exit(0);
        }
    }

    // Remove student by code
    private static void removeStudentByCode(StudentStack studentStack, Scanner scanner) {
        System.out.print("Устгах сурагчийн кодыг оруулна уу: ");
        String code = scanner.nextLine().trim();
        if (studentStack.removeStudentByCode(code)) {
            System.out.println("Сурагч амжилттай устгагдлаа.");
        } else {
            System.out.println("Сурагч олдсонгүй.");
        }
    }

    // Split students by GPA
    private static void splitStudentsByGPA(StudentStack studentStack, StudentStack goodStudents, StudentStack badStudents, Scanner scanner) {
        System.out.print("Голчийн хязгаар оруулна уу: ");
        double gpaThreshold = getValidGPA(scanner);
        studentStack.splitStack(goodStudents, badStudents, gpaThreshold);
    }

    // Merge student stacks
    private static void mergeStudentStacks(StudentStack studentStack, StudentStack goodStudents, StudentStack badStudents) {
        System.out.println("Сурагчдыг нэгтгэж байна...");
        while (!goodStudents.isEmpty()) {
            studentStack.push(goodStudents.pop());
        }
        while (!badStudents.isEmpty()) {
            studentStack.push(badStudents.pop());
        }
        System.out.println("Нэгтгэл амжилттай боллоо.");
    }

    // Show remaining capacity
    private static void showRemainingCapacity(StudentStack studentStack, int capacity) {
        int remainingCapacity = capacity - studentStack.size();
        System.out.println("Жагсаалтад үлдсэн зай: " + remainingCapacity);
    }

    // Show students by gender
    private static void showStudentsByGender(StudentStack studentStack, Scanner scanner) {
        System.out.print("Хүйс оруулна уу (1: Эрэгтэй, 2: Эмэгтэй): ");
        String input = scanner.nextLine().trim();
        String gender = "";
        if (input.equals("1")) {
            gender = "Эрэгтэй";
        } else if (input.equals("2")) {
            gender = "Эмэгтэй";
        } else {
            System.out.println("Алдаатай хүйс, дахин оруулна уу.");
            return;
        }
        studentStack.printStudentsByGender(gender);
    }
}