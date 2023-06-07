package skillguru.service.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ITemplateResolver;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.service.email.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private TemplateEngine templateEngine;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(emailSender, templateEngine);
    }

    @Test
    public void shouldRegisterMentorEmail() throws MessagingException {

        // GIVEN
//        RegisterMentorRequest mentorRequest = new RegisterMentorRequest();
//        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
//        Context context = new Context();
//
//        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);
//        when(templateEngine.process("", context)).thenReturn("wynik_przetwarzania");
//        // WHEN
////
//        mentorRequest.setEmail("hello@example.com");
//        mentorRequest.setAge(35);
//        mentorRequest.setFirstName("Norb");
//        mentorRequest.setLastName("Diango");
//
//
//        emailService.registerMentorEmail(mentorRequest);
//
//        // THEN
//        verify(emailService, times(1)).prepareEmail(eq("witaj@investalert.pl"), eq("hello@example.com"), eq("Rejestracja"), eq("registration"), any());
    }
}
