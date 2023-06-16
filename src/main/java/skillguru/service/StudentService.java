package skillguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import skillguru.exception.BadRequestException;
import skillguru.mapper.StudentMapper;
import skillguru.model.entities.Student;
import skillguru.model.request.RegisterStudentRequest;
import skillguru.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> registerStudent(RegisterStudentRequest registerStudentRequest) {
        if (checkIfExist(registerStudentRequest)) {
            throw new BadRequestException("Student with this data exist");
        }
        Student student = StudentMapper.studentBuilder(registerStudentRequest);
        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }

    private boolean checkIfExist(RegisterStudentRequest registerStudentRequest) {
        return studentRepository.findByEmail(registerStudentRequest.getEmail()).isPresent();
    }
}