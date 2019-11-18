package se.alten.schoolproject.dao;

import com.google.gson.JsonObject;
import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.ModelExceptions;
import se.alten.schoolproject.model.StudentModel;
import se.alten.schoolproject.transaction.StudentTransactionAccess;
import se.alten.schoolproject.transaction.TransactionExceptions;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SchoolDataAccess implements SchoolAccessLocal, SchoolAccessRemote {

    @Inject
    StudentTransactionAccess studentTransactionAccess;


    @Override
    public List listAllStudents() {

        List studentList = studentTransactionAccess.listAllStudents();
        List<StudentModel> studentModelList = new ArrayList<>();

        for (Object student: studentList) {
            studentModelList.add(new StudentModel((Student) student));
        }

        return studentModelList;

    }


    @Override
    public StudentModel addStudent(String studentJsonString) throws ModelExceptions.MissingValueException, TransactionExceptions.DuplicateEmailException {

        StudentModel studentModel = new StudentModel(studentJsonString);

        studentTransactionAccess.addStudent(new Student(studentModel));

        return studentModel;

    }


    @Override
    public void removeStudent(String email) throws TransactionExceptions.EmailNotFoundException {

        studentTransactionAccess.removeStudent(email);

    }


    @Override
    public StudentModel updateStudent(String firstName, String lastName, String email) throws ModelExceptions.MissingValueException, TransactionExceptions.EmailNotFoundException {

        JsonObject studentJson = new JsonObject();

        studentJson.addProperty("firstname", firstName);
        studentJson.addProperty("lastname", lastName);
        studentJson.addProperty("email", email);

        StudentModel studentModel = new StudentModel(studentJson.toString());

        studentTransactionAccess.updateStudent(new Student(studentModel));

        return studentModel;

    }


    @Override
    public StudentModel updateFirstName(String studentJsonString) throws ModelExceptions.MissingValueException, TransactionExceptions.LastNameAndEmailNotFoundException {

        StudentModel studentModel = new StudentModel(studentJsonString);

        studentTransactionAccess.updateFirstName(new Student(studentModel));

        return studentModel;

    }


    @Override
    public List findStudentsByLastName(String lastName) {

        List studentList = studentTransactionAccess.findStudentsByLastName(lastName);
        List<StudentModel> studentModelList = new ArrayList<>();

        for (Object student: studentList) {
            studentModelList.add(new StudentModel((Student) student));
        }

        return studentModelList;

    }

}
