package skillguru.service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ITemplateResolver;
import skillguru.model.request.RegisterMentorRequest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Set;

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

        prepareEmail("witaj@skillguru.pl", registerMentorRequest.getEmail(), "Rejestracja","registration", message);
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
