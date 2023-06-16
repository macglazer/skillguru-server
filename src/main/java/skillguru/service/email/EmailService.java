package skillguru.service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.model.request.RegisterStudentRequest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender emailSender;

    private final TemplateEngine templateEngine;


    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    private Context getContext() {
        return new Context();
    }

    public void registerMentorEmail(RegisterMentorRequest registerMentorRequest) throws MessagingException {
        Context context = getContext();

        context.setVariable("header", registerMentorRequest.getEmail());
        context.setVariable("title", "title");
        context.setVariable("name", registerMentorRequest.getEmail().split("@")[0]);

        MimeMessage message = emailSender.createMimeMessage();

        prepareEmail("witaj@skillguru.pl", registerMentorRequest.getEmail(), "Witaj mentorze!","registrationmentor", message);
    }

    public void registerStudentEmail(RegisterStudentRequest registerStudentRequest) throws MessagingException {
        Context context = getContext();
        context.setVariable("header", registerStudentRequest.getEmail());
        context.setVariable("title", "title");
        context.setVariable("name", registerStudentRequest.getEmail().split("@")[0]);

        MimeMessage message = emailSender.createMimeMessage();

        prepareEmail("witaj@skillguru.pl", registerStudentRequest.getEmail(), "Witaj studencie!","registrationmentor", message);
    }

    public void prepareEmail(String from, String emailFrom,String title, String template, MimeMessage message) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(emailFrom);
        helper.setSubject(title);
        String htmlBody = templateEngine.process(template, getContext());
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }
}
