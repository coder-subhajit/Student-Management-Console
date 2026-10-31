package com.subha.repo;

import com.subha.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepo {

    // Database configuration
    static String url = "jdbc:mysql://localhost:3306/student_db";
    static String username = "root";
    static String password = "MySQL@123";


    // =========================
    // CREATE STUDENT
    // =========================
    public static void createStudent(Student student) {

        String sql = """
                INSERT INTO students (name, email, age)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Student created successfully");
            } else {
                System.out.println("Student creation failed");
            }

        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    // =========================
    // UPDATE STUDENT
    // =========================
    public static void updateStudent(Student student, Long id) {

        String sql = """
                UPDATE students
                SET name = ?,
                    email = ?,
                    age = ?
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Student updated successfully");
            } else {
                System.out.println("Student not found");
            }

        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    // =========================
    // DELETE STUDENT
    // =========================
    public static void deleteStudent(Long id) {

        String sql = """
                DELETE FROM students
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setLong(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Student deleted successfully");
            } else {
                System.out.println("Student not found");
            }

        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    // =========================
    // GET STUDENT BY ID
    // =========================
    public static void getStudentById(Long id) {

        String sql = """
                SELECT id, name, email, age
                FROM students
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {

                    Student student = mapRow(rs);

                    System.out.println(student);

                } else {

                    System.out.println("Student not found");
                }
            }

        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    // =========================
    // MAP RESULTSET TO STUDENT
    // =========================
    private static Student mapRow(ResultSet rs) throws SQLException {

        Student student = new Student();

        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setAge(rs.getInt("age"));

        return student;
    }
}