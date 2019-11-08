package se.alten.schoolproject.dao;

import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.StudentModel;
import se.alten.schoolproject.transaction.StudentTransactionAccess;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Stateless
public class SchoolDataAccess implements SchoolAccessLocal, SchoolAccessRemote {

    private Student student = new Student();
    private StudentModel studentModel = new StudentModel();

    @Inject
    StudentTransactionAccess studentTransactionAccess;

    @Override
    public List listAllStudents() {
        //return studentTransactionAccess.listAllStudents();
        List studentList = studentTransactionAccess.listAllStudents();
        List<StudentModel> studentModelList = new ArrayList<>();
        //System.out.println("PRINT IN CONSOLE: listAllStudents");

        for (Object student: studentList) {
            studentModelList.add(new StudentModel().toModel((Student) student));
        }
        return studentModelList;
    }

    @Override
    public StudentModel addStudent(String studentJsonString) {
        Student studentToAdd = student.toEntity(studentJsonString);
        boolean checkForEmptyVariables = Stream.of(studentToAdd.getFirstName(), studentToAdd.getLastName(), studentToAdd.getEmail()).anyMatch(String::isBlank);

        if (checkForEmptyVariables) {
            studentToAdd.setFirstName("empty");
            return studentModel.toModel(studentToAdd);
        } else {
            studentTransactionAccess.addStudent(studentToAdd);
            return studentModel.toModel(studentToAdd);
        }
    }

    @Override
    public void removeStudent(String email) {
        studentTransactionAccess.removeStudent(email);
    }

    @Override
    public void updateStudent(String firstName, String lastName, String email) {
        studentTransactionAccess.updateStudent(firstName, lastName, email);
    }

    @Override
    public void updateStudentPartial(String studentJsonString) {
        Student studentToUpdate = student.toEntity(studentJsonString);
        studentTransactionAccess.updateStudentPartial(studentToUpdate);
    }
}
