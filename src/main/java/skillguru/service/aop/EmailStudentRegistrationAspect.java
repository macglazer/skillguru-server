package skillguru.service.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.model.request.RegisterStudentRequest;
import skillguru.service.email.EmailService;

import javax.mail.MessagingException;

@Aspect
@Component
public class EmailStudentRegistrationAspect {

    private EmailService emailService;

    @Autowired
    public EmailStudentRegistrationAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @After("execution(public org.springframework.http.ResponseEntity skillguru.service.StudentService.registerStudent(skillguru.model.request.RegisterStudentRequest)) && args(registerStudentRequest)")
    public void sendRegistrationEmail(RegisterStudentRequest registerStudentRequest) throws MessagingException {
            emailService.registerStudentEmail(registerStudentRequest);
    }
}