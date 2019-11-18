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

    private String firstname;
    private String lastname;
    private String email;


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

    }


    private static boolean empty(String string) {

        return string == null || string.isBlank();

    }

}
