package se.alten.schoolproject.dao;

import com.google.gson.JsonObject;
import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.ModelExceptions;
import se.alten.schoolproject.model.StudentModel;
import se.alten.schoolproject.transaction.StudentTransactionAccess;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
            studentModelList.add(new StudentModel((Student) student));
        }

        return studentModelList;

    }

    @Override
    public StudentModel addStudent(String studentJsonString) throws ModelExceptions.MissingValueException {
        /*Student studentToAdd = student.toEntity(studentJsonString);
        boolean checkForEmptyVariables = Stream.of(studentToAdd.getFirstName(), studentToAdd.getLastName(), studentToAdd.getEmail()).anyMatch(String::isBlank);

        if (checkForEmptyVariables) {
            studentToAdd.setFirstName("empty");
            return studentModel.toModel(studentToAdd);
        } else {
            studentTransactionAccess.addStudent(studentToAdd);
            return studentModel.toModel(studentToAdd);
        }*/

        StudentModel studentModel = new StudentModel(studentJsonString);

        studentTransactionAccess.addStudent(new Student(studentModel));

        return studentModel;

    }

    @Override
    public void removeStudent(String email) {
        studentTransactionAccess.removeStudent(email);
    }

    @Override
    public void updateStudent(String firstName, String lastName, String email) throws ModelExceptions.MissingValueException {

        JsonObject studentJson = new JsonObject();

        studentJson.addProperty("firstname", firstName);
        studentJson.addProperty("lastname", lastName);
        studentJson.addProperty("email", lastName);

        StudentModel studentModel = new StudentModel(studentJson.getAsString());

        studentTransactionAccess.updateStudent(new Student(studentModel));
    }

    @Override
    public void updateStudentPartial(String studentJsonString) throws ModelExceptions.MissingValueException {

        StudentModel studentModel = new StudentModel(studentJsonString);

        studentTransactionAccess.updateStudentPartial(new Student(studentModel));

    }

}
