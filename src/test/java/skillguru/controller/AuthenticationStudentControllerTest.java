package skillguru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import skillguru.model.request.RegisterStudentRequest;
import skillguru.service.StudentService;

import static org.mockito.Mockito.when;

class AuthenticationStudentControllerTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private AuthenticationStudentController studentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

    }

    @Test
    void shouldRegisterWork() throws Exception {
        // given
        RegisterStudentRequest registerStudentRequest = new RegisterStudentRequest();

        // when
        when(service.registerStudent(registerStudentRequest)).thenReturn(ResponseEntity.ok().build());

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/student/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerStudentRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}