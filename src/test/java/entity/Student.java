package entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yejinbiao
 * @create 2016-12-21-10:01
 */
@Setter
@Getter
@ToString
public class Student {

    private String name;

    private int age;

    public Student() {
    }
    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        return 1;
    }
}
