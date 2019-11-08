package se.alten.schoolproject.rest;

import lombok.NoArgsConstructor;
import se.alten.schoolproject.dao.SchoolAccessLocal;
import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@NoArgsConstructor
@Path("/student")
public class StudentController {

    @Inject
    private SchoolAccessLocal sal;

    @GET
    @Path("/getallstudents")
    @Produces({"application/JSON"})
    public Response showStudents() {
        try {
            List students = sal.listAllStudents();
            return Response.ok(students).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Path("/addstudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/JSON"})
    public Response addStudent(String studentJsonString) {
        System.out.println("PRINT IN CONSOLE: addStudent");
        try {

            StudentModel answer = sal.addStudent(studentJsonString);

            System.out.println("PRINT IN CONSOLE: " + answer.getFirstName());

            switch ( answer.getFirstName()) {
                case "empty":
                    return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"Fill in all details please\"}").build();
                case "duplicate":
                    return Response.status(Response.Status.EXPECTATION_FAILED).entity("{\"Email already registered!\"}").build();
                default:
                    return Response.ok(answer).build();
            }
        } catch ( Exception e ) {
            System.out.println("PRINT IN CONSOLE: EXCEPTION IN STUDENTCONTROLLER!");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("deletestudent/{email}")
    public Response deleteUser( @PathParam("email") String email) {
        try {
            sal.removeStudent(email);
            return Response.ok().build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("updatestudent")
    public void updateStudent( @QueryParam("firstname") String firstName, @QueryParam("lastname") String lastName, @QueryParam("email") String email) {
        sal.updateStudent(firstName, lastName, email);
    }

    @PATCH
    @Path("updatepartialstudent")
    public void updatePartialStudent(String studentJsonString) {
        sal.updateStudentPartial(studentJsonString);
    }
}
