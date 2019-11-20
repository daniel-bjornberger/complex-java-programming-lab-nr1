# complex-java-programming-lab-nr1
Lab nr 1 in the course Complex Java-programming, ITHS  
MySQL Connector/J version: 8.0.18

## To deploy
Command line: wildfly:undeploy clean:clean wildfly:deploy

## Endpoints
### get all students
Address: http://localhost:8080/school/student/getallstudents  
HTTP method: GET  
Body: None  
An array of Json objects representing the students will be returned.

### add student
Address: http://localhost:8080/school/student/addstudent  
HTTP method: POST  
Body: Json, in the format:
<pre>
{
	"firstname": "anders",
	"lastname": "andersson",
	"email": "anders@gmail.com"
}
</pre>
A Json object representing the student will be returned, if all three posted attribute–value pairs are present and not blank/empty.

### delete student
Address: http://localhost:8080/school/student/deletestudent/{email}  
HTTP method: DELETE  
Body: None  
Example:
<pre>
http://localhost:8080/school/student/deletestudent/anders@gmail.com
</pre>
A message (mediatype: text/plain) indicating success or failure will be returned.
