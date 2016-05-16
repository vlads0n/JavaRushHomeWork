package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student studentWithAverageGrade = null;
        for (Student student : students)
            if (student.getAverageGrade() == averageGrade) {
                studentWithAverageGrade = student;
                break;
            }
        return studentWithAverageGrade;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentWithMaxAverageGrade = students.get(0);
        double maxAverage = students.get(0).getAverageGrade();
        for (Student student : students)
        {
            if (maxAverage < student.getAverageGrade()) {
                maxAverage = student.getAverageGrade();
                studentWithMaxAverageGrade = student;
            }
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        Student studentWithMinAverageGrade = students.get(0);
        double minAverage = students.get(0).getAverageGrade();
        for (Student student : students)
        {
            if (minAverage > student.getAverageGrade())
            {
                minAverage = student.getAverageGrade();
                studentWithMinAverageGrade = student;
            }
        }
        return studentWithMinAverageGrade;
    }

    public void expel(Student student)
    {
        students.remove(student);
    }
}
