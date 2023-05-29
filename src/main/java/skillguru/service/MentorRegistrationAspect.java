package skillguru.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.service.email.EmailService;

@Aspect
@Component
public class MentorRegistrationAspect {

    private EmailService emailService;

    @Autowired
    public MentorRegistrationAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @AfterReturning("execution(public * com.example.MentorController.registerMentor(..)) && args(registerMentorRequest)")
    public void sendRegistrationEmail(RegisterMentorRequest registerMentorRequest) {
        String mentorEmail = registerMentorRequest.getEmail();
        String message = "Dear mentor, you have been successfully registered.";
        System.out.println("TEST TEST TEST");
//        emailService.sendEmail(mentorEmail, "Registration Confirmation", message);
    }
}
