package se.alten.schoolproject.model;

import lombok.*;
import se.alten.schoolproject.entity.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public StudentModel toModel(Student student) {
        StudentModel studentModel = new StudentModel();
        switch (student.getFirstName()) {
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
        }
    }
}
