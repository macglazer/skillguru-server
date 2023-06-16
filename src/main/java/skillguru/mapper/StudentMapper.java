package skillguru.mapper;

import skillguru.model.entities.Mentor;
import skillguru.model.entities.Student;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.model.request.RegisterStudentRequest;

public class StudentMapper {

    public static Student studentBuilder(RegisterStudentRequest registerStudentRequest ) {
        return Student.builder()
//                .uuid(UUID.randomUUID().toString())
                .firstName(registerStudentRequest.getFirstName())
                .lastName(registerStudentRequest.getLastName())
                .email(registerStudentRequest.getEmail())
                .build();
    }
}