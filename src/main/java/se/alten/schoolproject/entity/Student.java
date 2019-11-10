package se.alten.schoolproject.entity;

import lombok.*;
import se.alten.schoolproject.model.StudentModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    public Student(StudentModel studentModel) {
        this.firstName = studentModel.getFirstname();
        this.lastName  = studentModel.getLastname();
        this.email     = studentModel.getEmail();
    }


    /*public Student toEntityFromModel(StudentModel studentModel) {

        Student student = new Student();

        student.setFirstName(studentModel.getFirstName());
        student.setLastName(studentModel.getLastName());
        student.setEmail(studentModel.getEmail());

        return student;

    }*/


    /*public Student toEntity(String studentJsonString) {
        JsonReader reader = Json.createReader(new StringReader(studentJsonString));

        JsonObject jsonObject = reader.readObject();

        Student student = new Student();
        if ( jsonObject.containsKey("firstname")) {
            student.setFirstName(jsonObject.getString("firstname"));
        } else {
            student.setFirstName("");
        }

        if ( jsonObject.containsKey("lastname")) {
            student.setLastName(jsonObject.getString("lastname"));
        } else {
            student.setLastName("");
        }

        if ( jsonObject.containsKey("email")) {
            student.setEmail(jsonObject.getString("email"));
        } else {
            student.setEmail("");
        }

        //Student student = new Gson().fromJson(studentJsonString, Student.class);

        //return new Gson().fromJson(studentJsonString, Student.class);

        return student;

    }*/

}
