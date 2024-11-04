import java.util.Scanner;
import java.util.ArrayList;

class Student {
    String name;
    int age;
    String code;
    double gpa;
    String gender;

    Student(String name, int age, String code, double gpa, String gender) {
        this.name = name;
        this.age = age;
        this.code = code;
        this.gpa = gpa;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Нэр: " + name + ", Нас: " + age + ", Код: " + code + ", GPA: " + gpa + ", Хүйс: " + gender;
    }
}

public class StudentArrayList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Амина", 20, "01", 3.5, "Female"));
        students.add(new Student("Болд", 21, "03", 3.2, "Male"));

        while (true) {
            System.out.println("\n1. Бүх сурагчдын мэдээлэл харах");
            System.out.println("2. Сурагч нэмэх");
            System.out.println("3. Сурагч хасах");
            System.out.println("4. Сурагчийн мэдээллийг өөрчлөх");
            System.out.println("5. Гарах");
            System.out.print("Сонголт: ");
            String choiceInput = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Буруу сонголт. Зөвхөн тоогоор оруулна уу.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (students.isEmpty()) {
                        System.out.println("Сурагчид байхгүй байна.");
                    } else {
                        System.out.println("\nСурагчид:");
                        for (int i = 0; i < students.size(); i++) {
                            System.out.println("№ " + (i + 1) + ": " + students.get(i));
                        }
                    }
                    break;

                case 2:
                    System.out.print("Сурагчийн нэрийг оруулна уу: ");
                    String name = scanner.nextLine().trim();

                    int age;
                    while (true) {
                        System.out.print("Сурагчийн нас: ");
                        String ageInput = scanner.nextLine().trim();
                        try {
                            age = Integer.parseInt(ageInput);
                            if (age < 0 || age > 100) {
                                System.out.println("Нас 0-с 100 хүртэл байх ёстой. Дахин оролдоно уу.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Нас зөвхөн тоогоор оруулна уу. Дахин оролдоно уу.");
                        }
                    }

                    System.out.print("Сурагчийн кодыг оруулна уу: ");
                    String code = scanner.nextLine().trim();

                    double gpa;
                    while (true) {
                        System.out.print("Сурагчийн голчийг оруулна уу: ");
                        String gpaInput = scanner.nextLine().trim();
                        try {
                            gpa = Double.parseDouble(gpaInput);
                            if (gpa < 0 || gpa > 4) {
                                System.out.println("GPA 0-с 4 хүртэл байх ёстой. Дахин оролдоно уу.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("GPA зөвхөн тоогоор оруулна уу. Дахин оролдоно уу.");
                        }
                    }

                    String gender = selectGender(scanner);

                    students.add(new Student(name, age, code, gpa, gender));
                    System.out.println("Сурагчийг нэмлээ!");
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("Сурагчид байхгүй байна.");
                        break;
                    }
                    System.out.print("Устгах сурагчийн дугаарыг оруулна уу (№): ");
                    String indexToRemoveInput = scanner.nextLine().trim();
                    int indexToRemove;
                    try {
                        indexToRemove = Integer.parseInt(indexToRemoveInput);
                        if (indexToRemove < 1 || indexToRemove > students.size()) {
                            System.out.println("Алдаатай дугаар байна.");
                            break;
                        }
                        Student removedStudent = students.remove(indexToRemove - 1);
                        System.out.println("№ " + indexToRemove + " устгагдлаа: " + removedStudent.name);
                    } catch (NumberFormatException e) {
                        System.out.println("Алдаатай дугаар байна.");
                    }
                    break;

                case 4:
                    if (students.isEmpty()) {
                        System.out.println("Сурагчид байхгүй байна.");
                        break;
                    }
                    System.out.print("Өөрчлөх сурагчийн дугаарыг оруулна уу (№): ");
                    String indexToUpdateInput = scanner.nextLine().trim();
                    int indexToUpdate;
                    try {
                        indexToUpdate = Integer.parseInt(indexToUpdateInput);
                        if (indexToUpdate < 1 || indexToUpdate > students.size()) {
                            System.out.println("Алдаатай дугаар байна.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Алдаатай дугаар байна.");
                        break;
                    }

                    Student student = students.get(indexToUpdate - 1);
                    System.out.println("Одоогийн мэдээлэл: " + student);
                    boolean updating = true;

                    while (updating) {
                        System.out.println("\nӨөрчлөх нь:");
                        System.out.println("1. Нэр");
                        System.out.println("2. Нас");
                        System.out.println("3. Код");
                        System.out.println("4. Голч");
                        System.out.println("5. Хүйс");
                        System.out.println("6. Буцах");
                        System.out.print("Сонголт: ");
                        String updateChoiceInput = scanner.nextLine().trim();
                        int updateChoice;

                        try {
                            updateChoice = Integer.parseInt(updateChoiceInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Алдаатай сонголт.");
                            continue;
                        }

                        switch (updateChoice) {
                            case 1:
                                System.out.print("Шинэ нэрийг оруулна уу: ");
                                String newName = scanner.nextLine().trim();
                                student.name = newName;
                                System.out.println("Нэр амжилттай өөрчлөгдлөө!");
                                break;

                            case 2:
                                while (true) {
                                    System.out.print("Шинэ насыг оруулна уу: ");
                                    String newAgeInput = scanner.nextLine().trim();
                                    try {
                                        int newAge = Integer.parseInt(newAgeInput);
                                        if (newAge < 0 || newAge > 100) {
                                            System.out.println("Нас 0-с 100 хүртэл байх ёстой. Дахин оролдоно уу.");
                                            continue;
                                        }
                                        student.age = newAge;
                                        System.out.println("Нас амжилттай өөрчлөгдлөө!");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Нас зөвхөн тоогоор оруулна уу. Дахин оролдоно уу.");
                                    }
                                }
                                break;

                            case 3:
                                System.out.print("Шинэ кодыг оруулна уу: ");
                                String newCode = scanner.nextLine().trim();
                                student.code = newCode;
                                System.out.println("Код амжилттай өөрчлөгдлөө!");
                                break;

                            case 4:
                                while (true) {
                                    System.out.print("Шинэ голчийг оруулна уу: ");
                                    String newGpaInput = scanner.nextLine().trim();
                                    try {
                                        double newGpa = Double.parseDouble(newGpaInput);
                                        if (newGpa < 0 || newGpa > 4) {
                                            System.out.println("GPA 0-с 4 хүртэл байх ёстой. Дахин оролдоно уу.");
                                            continue;
                                        }
                                        student.gpa = newGpa;
                                        System.out.println("GPA амжилттай өөрчлөгдлөө!");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("GPA зөвхөн тоогоор оруулна уу. Дахин оролдоно уу.");
                                    }
                                }
                                break;

                            case 5:
                                String newGender = selectGender(scanner);
                                student.gender = newGender;
                                System.out.println("Хүйс амжилттай өөрчлөгдлөө!");
                                break;

                            case 6:
                                System.out.println("Буцах.");
                                updating = false;
                                break;

                            default:
                                System.out.println("Алдаатай сонголт.");
                                break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("Програм дууслаа.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Алдаатай сонголт. Зөв сонголт оруулна уу.");
                    break;
            }
        }
    }

    /**
     * Хүйс сонгох функц
     * Хэрэглэгчдэд зөвхөн "Male" болон "Female" гэсэн хоёр сонголт олгоно.
     */
    private static String selectGender(Scanner scanner) {
        while (true) {
            System.out.println("Сурагчийн хүйсийг сонгоно уу:");
            System.out.println("1. Эрэгтэй");
            System.out.println("2. Эмэгтэй");
            System.out.print("Сонголт: ");
            String genderChoice = scanner.nextLine().trim();

            switch (genderChoice) {
                case "1":
                    return "Male";
                case "2":
                    return "Female";
                default:
                    System.out.println("Алдаатай сонголт. Зөв сонголт оруулна уу.");
                    break;
            }
        }
    }
}