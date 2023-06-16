package skillguru.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import skillguru.exception.BadRequestException;
import skillguru.mapper.MentorMapper;
import skillguru.model.entities.Mentor;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.repository.MentorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static skillguru.mapper.MentorMapper.mentorBuilder;

class MentorServiceTest {

    @Mock
    MentorRepository repository;
    @InjectMocks
    MentorService service;

    @BeforeEach()
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnStatus200(){
        // given
        RegisterMentorRequest registerMentorRequest = new RegisterMentorRequest();

        // when
        when(repository.findByEmail(registerMentorRequest.getEmail())).thenReturn(Optional.empty());
        ResponseEntity<?> result = service.registerMentor(registerMentorRequest);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldThrowExceptionBadRequestExceptionWhenMentorIsExist(){
        // given
        RegisterMentorRequest registerMentorRequest = new RegisterMentorRequest();

        // when
        when(repository.findByEmail(registerMentorRequest.getEmail())).thenReturn(Optional.of(new Mentor()));


        // then
        Assertions.assertThrows(BadRequestException.class,()-> {
            service.registerMentor(registerMentorRequest);
        });
    }

    @Test
    void shouldInvokeOneTimeSaveMethod(){
        // given
        RegisterMentorRequest registerMentorRequest = new RegisterMentorRequest();

        // when
        when(repository.findByEmail(registerMentorRequest.getEmail())).thenReturn(Optional.empty());
        service.registerMentor(registerMentorRequest);
        // then

        verify(repository,times(1)).save(new Mentor());
    }

    @Test
    void shouldMapperCorrectlyMapRegisterMentorRequestToMentor(){
        // given
        RegisterMentorRequest registerMentorRequest = new RegisterMentorRequest();
        registerMentorRequest.setFirstName("John");
        registerMentorRequest.setLastName("Novak");
        registerMentorRequest.setAge(35);
        registerMentorRequest.setEmail("john@john.com");

       // when
        when(repository.findByEmail(registerMentorRequest.getEmail())).thenReturn(Optional.empty());
        service.registerMentor(registerMentorRequest);
        Mentor mentor = mentorBuilder(registerMentorRequest);


        // then
        Assertions.assertEquals(registerMentorRequest.getEmail(),mentor.getEmail());
        Assertions.assertEquals(registerMentorRequest.getAge(),mentor.getAge());
        Assertions.assertEquals(registerMentorRequest.getFirstName(),mentor.getFirstName());
        Assertions.assertEquals(registerMentorRequest.getLastName(),mentor.getLastName());
    }

}