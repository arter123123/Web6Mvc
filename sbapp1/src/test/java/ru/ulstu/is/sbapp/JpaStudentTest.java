package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Student;
import ru.ulstu.is.sbapp.student.service.StudentNotFoundException;
import ru.ulstu.is.sbapp.student.service.StudentService;

import java.util.List;

@SpringBootTest
public class JpaStudentTest {
    private static final Logger log = LoggerFactory.getLogger(JpaStudentTest.class);

    @Autowired
    private StudentService studentService;

    //Создает новую строчку в бд, выводит исключение, если нету пользователя с таким id в бд
    @Test
    void testStudentCreate() {
        studentService.deleteAllStudents();
        final Student student = studentService.addStudent("Иван", "Иванов");
        log.info(student.toString());
        Assertions.assertNotNull(student.getId());
    }

    //Добавляет новую строчку в бд, ищет строчку по id. Выводит исключение, если строчки не равны
    @Test
    void testStudentRead() {
        studentService.deleteAllStudents();
        final Student student = studentService.addStudent("Иван", "Иванов");
        log.info(student.toString());
        final Student findStudent = studentService.findStudent(student.getId());
        log.info(findStudent.toString());
        Assertions.assertEquals(student, findStudent);
    }

    //Выведет исключение, если код НЕ вызовет исключение типа ApplicationException
    @Test
    void testStudentReadNotFound() {
        studentService.deleteAllStudents();
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.findStudent(-1L));
    }

    //Выведет исключение, если в списке студентов будет больше студентов, чем 2
    @Test
    void testStudentReadAll() {
        studentService.deleteAllStudents();
        studentService.addStudent("Иван", "Иванов");
        studentService.addStudent("Петр", "Петров");
        final List<Student> students = studentService.findAllStudents();
        log.info(students.toString());
        Assertions.assertEquals(students.size(), 2);
    }

    //Выведет исключение, если в списке будет больше студентов, чем 0
    @Test
    void testStudentReadAllEmpty() {
        studentService.deleteAllStudents();
        final List<Student> students = studentService.findAllStudents();
        log.info(students.toString());
        Assertions.assertEquals(students.size(), 0);
    }
}
