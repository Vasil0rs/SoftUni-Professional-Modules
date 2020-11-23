package ValidPerson;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
    }

    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        if (firstName .isBlank()) {
            throw new IllegalArgumentException("invalid name!");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        if (lastName.isBlank()) {
            throw new IllegalArgumentException("invalid name!");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException(
                    "Age cannot be outside from range [0...120]!");
        }
        this.age = age;
    }
}
