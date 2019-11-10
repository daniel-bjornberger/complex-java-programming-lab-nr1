package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class StudentTransaction implements StudentTransactionAccess{

    @PersistenceContext(unitName="school")
    private EntityManager entityManager;


    @Override
    public List listAllStudents() {
        Query query = entityManager.createQuery("SELECT s from Student s");
        return query.getResultList();
    }


    @Override
    public void addStudent(Student student) {
        try {
            entityManager.persist(student);
            entityManager.flush();
        } catch (PersistenceException pe) {
            //student.setFirstName("duplicate");
            System.out.println("PERSISTENCE EXCEPTION!");
        }
    }


    @Override
    public void removeStudent(String email) {
        //JPQL Query
        Query query = entityManager.createQuery("DELETE FROM Student s WHERE s.email = :email");

        //Native Query
        //Query query = entityManager.createNativeQuery("DELETE FROM student WHERE email = :email", Student.class);

        query.setParameter("email", email)
             .executeUpdate();
    }


    @Override
    public void updateStudent(Student student) {
        Query updateQuery = entityManager.createNativeQuery("UPDATE student SET firstname = :firstname, lastname = :lastname WHERE email = :email", Student.class);
        updateQuery.setParameter("firstname", student.getFirstName())
                   .setParameter("lastname", student.getLastName())
                   .setParameter("email", student.getEmail())
                   .executeUpdate();
    }


    @Override
    public void updateStudentPartial(Student student) {
        Student studentFound = (Student) entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email")
                .setParameter("email", student.getEmail()).getSingleResult();

        Query query = entityManager.createQuery("UPDATE Student SET firstname = :studentfirstname WHERE email = :email");
        query.setParameter("studentfirstname", student.getFirstName())
                .setParameter("email", studentFound.getEmail())
                .executeUpdate();
    }

}
