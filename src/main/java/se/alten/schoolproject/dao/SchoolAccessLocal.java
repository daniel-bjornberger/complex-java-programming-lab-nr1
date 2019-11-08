package se.alten.schoolproject.dao;

import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SchoolAccessLocal {

    List listAllStudents() throws Exception;

    StudentModel addStudent(String studentJsonString);

    void removeStudent(String email);

    void updateStudent(String firstName, String lastName, String email);

    void updateStudentPartial(String studentJsonString);
}
