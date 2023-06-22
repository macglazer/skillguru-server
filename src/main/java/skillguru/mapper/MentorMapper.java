package skillguru.mapper;

import skillguru.model.entities.Mentor;
import skillguru.model.request.RegisterMentorRequest;
import skillguru.model.request.zencal.ZencalProfileCreateRequest;

import java.util.UUID;

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

    public static Mentor zencalProfileToMentorBuilder(ZencalProfileCreateRequest registerMentorRequest ) {
        return Mentor.builder()
                .uuid(UUID.randomUUID())
                .zecnalId(registerMentorRequest.getZencalId())
                .firstName(registerMentorRequest.getFirstName())
                .lastName(registerMentorRequest.getLastName())
                .email(registerMentorRequest.getEmail())
                .build();
    }
}
