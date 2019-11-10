package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StudentTransactionAccess {
    List listAllStudents();
    void addStudent(Student student);
    void removeStudent(String email);
    void updateStudent(Student student);
    void updateStudentPartial(Student student);
}
