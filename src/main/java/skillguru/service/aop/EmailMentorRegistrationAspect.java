package skillguru.service.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.service.email.EmailService;

import javax.mail.MessagingException;

@Aspect
@Component
public class EmailMentorRegistrationAspect {

    private EmailService emailService;

    @Autowired
    public EmailMentorRegistrationAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @After("execution(public org.springframework.http.ResponseEntity skillguru.service.MentorService.registerMentor(skillguru.model.request.RegisterMentorRequest)) && args(registerMentorRequest)")
    public void sendRegistrationEmail(RegisterMentorRequest registerMentorRequest) throws MessagingException {
            emailService.registerMentorEmail(registerMentorRequest);
    }
}