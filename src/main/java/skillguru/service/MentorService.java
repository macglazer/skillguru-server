package skillguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import skillguru.exception.BadRequestException;
import skillguru.model.entities.Mentor;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.repository.MentorRepository;

import static skillguru.mapper.MentorMapper.mentorBuilder;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public ResponseEntity<?> registerMentor(RegisterMentorRequest registerMentorRequest) {
        if (checkIfExist(registerMentorRequest)) {
            throw new BadRequestException("Mentor with this data");
        }
        Mentor mentor = mentorBuilder(registerMentorRequest);
        mentorRepository.save(mentor);
        return ResponseEntity.ok().build();
    }

    private boolean checkIfExist(RegisterMentorRequest registerMentorRequest) {
        return mentorRepository.findByEmail(registerMentorRequest.getEmail()).isPresent();
    }
}
