package se.alten.schoolproject.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.*;
import se.alten.schoolproject.entity.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentModel {

    //private Long id;
    private String firstname;
    private String lastname;
    private String email;

    /*public StudentModel toModel(Student student) {

        StudentModel studentModel = new StudentModel();
        *//*switch (student.getFirstName()) {
            case "empty":
                studentModel.setFirstName("empty");
                return studentModel;
            case "duplicate":
                studentModel.setFirstName("duplicate");
                return studentModel;
            default:
                studentModel.setFirstName(student.getFirstName());
                studentModel.setLastName(student.getLastName());
                studentModel.setEmail(student.getEmail());
                return studentModel;
        }*//*

        studentModel.setFirstName(student.getFirstName());
        studentModel.setLastName(student.getLastName());
        studentModel.setEmail(student.getEmail());
        return studentModel;

    }*/


    public StudentModel(Student student) {
        this.setFirstname(student.getFirstName());
        this.setLastname(student.getLastName());
        this.setEmail(student.getEmail());
    }



    public StudentModel(String studentJsonString) throws JsonSyntaxException, ModelExceptions.MissingValueException {

        StudentModel temp = new Gson().fromJson(studentJsonString, StudentModel.class);

        if (empty(temp.getFirstname()) || empty(temp.getLastname()) || empty(temp.getEmail())) {
            throw new ModelExceptions.MissingValueException();
        }

        this.firstname = temp.getFirstname();
        this.lastname  = temp.getLastname();
        this.email     = temp.getEmail();

        System.out.println("PRINT IN CONSOLE; STUDENTMODEL; firstname = " + this.firstname);

    }


    private static boolean empty(String s) {
        return s == null || s.isBlank();
    }


    /*public StudentModel toModelFromJsonString(String studentJsonString) {

        return new Gson().fromJson(studentJsonString, StudentModel.class);

    }*/

}
