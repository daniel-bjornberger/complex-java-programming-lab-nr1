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

}
