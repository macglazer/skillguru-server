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
import skillguru.model.request.RegisterMentorRequest;
import skillguru.service.MentorService;

import static org.mockito.Mockito.when;

class AuthenticationMentorControllerTest {

    @Mock
    private MentorService service;

    @InjectMocks
    private AuthenticationMentorController authenticationMentorController;

    private MockMvc mockMvc;


    @BeforeEach()
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationMentorController).build();

    }

    @Test
    void shouldRegisterWork() throws Exception {
        // given
        RegisterMentorRequest registerMentorRequest = new RegisterMentorRequest();

        // when
        when(service.registerMentor(registerMentorRequest)).thenReturn(ResponseEntity.ok().build());

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerMentorRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

