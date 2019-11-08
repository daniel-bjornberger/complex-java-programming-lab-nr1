package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StudentTransactionAccess {
    List listAllStudents();
    Student addStudent(Student student);
    void removeStudent(String email);
    void updateStudent(String firstName, String lastName, String email);
    void updateStudentPartial(Student student);
}
