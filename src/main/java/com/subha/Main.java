package com.subha;

import com.subha.model.Student;
import com.subha.repo.StudentRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        StudentRepo sturepo=new StudentRepo();

        //sturepo.createStudent(new Student("rahul","rahul@gmail.com",21));
        //sturepo.updateStudent(new Student("xubha","xubha@gmail.com",21),1L);
        //sturepo.deleteStudent(4L);
        StudentRepo.getStudentById(5L);
    }
}