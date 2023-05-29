package skillguru.mapper;

import skillguru.model.entities.Mentor;
import skillguru.model.request.RegisterMentorRequest;

public class MentorMapper {

    public static Mentor mentorBuilder(RegisterMentorRequest registerMentorRequest ) {
        return Mentor.builder()
//                .uuid(UUID.randomUUID().toString())
                .firstName(registerMentorRequest.getFirstName())
                .lastName(registerMentorRequest.getLastName())
                .age(registerMentorRequest.getAge())
                .email(registerMentorRequest.getEmail())
                .build();
    }
}
