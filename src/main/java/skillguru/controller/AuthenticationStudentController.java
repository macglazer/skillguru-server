package skillguru.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skillguru.model.entities.Student;
import skillguru.model.request.RegisterStudentRequest;
import skillguru.service.StudentService;

@RestController
@Slf4j
@CrossOrigin(origins = {"*"})
@RequestMapping(path = "/auth/student")
public class AuthenticationStudentController {

    private final StudentService studentService;

    public AuthenticationStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterStudentRequest registerStudentRequest) throws Exception {
        return studentService.registerStudent(registerStudentRequest);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/login-student")
    public ResponseEntity<?> login(@RequestBody RegisterStudentRequest student){
        return studentService.loginStudent(student);

    }
}
