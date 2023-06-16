package skillguru.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import skillguru.exception.BadRequestException;
import skillguru.model.entities.Student;
import skillguru.model.request.RegisterStudentRequest;
import skillguru.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    private StudentService service;

    @BeforeEach()
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnStatus200() {
        // given
        RegisterStudentRequest registerStudentRequest = new RegisterStudentRequest();

        // when
        when(studentRepository.findByEmail(registerStudentRequest.getEmail())).thenReturn(Optional.empty());
        ResponseEntity<?> response = service.registerStudent(registerStudentRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldThrowExceptionWhenStudentIsExist() {
        // given
        RegisterStudentRequest registerStudentRequest = new RegisterStudentRequest();

        // when
        when(studentRepository.findByEmail(registerStudentRequest.getEmail())).thenReturn(Optional.of( new Student()));

        // then
        assertThrows(BadRequestException.class,()-> {
            service.registerStudent(registerStudentRequest);
        });
    }


    @Test
    void shouldStudentRepositorySaveInvokeOnes(){
        // given
        RegisterStudentRequest registerStudentRequest = new RegisterStudentRequest();

        // when
        when(studentRepository.findByEmail(registerStudentRequest.getEmail())).thenReturn(Optional.empty());
         service.registerStudent(registerStudentRequest);

        // then
        verify(studentRepository,times(1)).save(new Student());
    }

}