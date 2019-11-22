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
A Json object representing the student will be returned, if all three Json attribute–value pairs are present and not blank/empty.

### delete student
Address: http://localhost:8080/school/student/deletestudent/{email}  
HTTP method: DELETE  
Body: None  
Example:
<pre>
http://localhost:8080/school/student/deletestudent/anders@gmail.com
</pre>
A message (mediatype: text/plain) indicating success or failure will be returned.

### update student
Address: http://localhost:8080/school/student/updatestudent?firstname={firstname}&lastname={lastname}&email={email}  
HTTP method: PUT  
Body: None  
Example:
<pre>
http://localhost:8080/school/student/updatestudent?firstname=peter&lastname=karlsson&email=anders@gmail.com
</pre>
A Json object representing the updated student will be returned, if all three query parameter attribute–value pairs are present and not blank/empty.

### update first name
Address: http://localhost:8080/school/student/deletestudent/{email}  
HTTP method: PATCH  
Body: Json, in the format:
<pre>
{
	"firstname": "bertil",
	"lastname": "andersson",
	"email": "anders@gmail.com"
}
</pre>
A Json object representing the updated student will be returned, if all three Json attribute–value pairs are present and not blank/empty.

### find students by last name
Address: http://localhost:8080/school/student/findstudentsbylastname/{lastname}  
HTTP method: GET  
Body: None
Example:
<pre>
http://localhost:8080/school/student/findstudentsbylastname/andersson
</pre>
An array of Json objects representing the students with the requested last name will be returned. If no students have the requested last name, an empty array will be returned.
<br/><br/><br/><br/>
___
<br/>
"Vem som gjort vad": Jag har arbetat ensam på den här labben, så allt är gjort av mig.  
<br/>
"Stötte ni på problem som ni inte förstod hur man ska lösa": Nej, de problem som har jag har stött på har jag lyckats lösa. Den stora frågan är om det finns något i koden som jag borde ha rättat, men som jag inte har insett att jag borde ha ändrat. Det är möjligt.
