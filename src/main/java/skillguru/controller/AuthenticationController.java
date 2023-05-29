package skillguru.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.service.MentorService;

@RestController
@Slf4j
@CrossOrigin(origins = {"*"})
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final MentorService mentorService;

    public AuthenticationController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterMentorRequest registerMentorRequest) throws Exception {
        return mentorService.registerMentor(registerMentorRequest);
    }
}
