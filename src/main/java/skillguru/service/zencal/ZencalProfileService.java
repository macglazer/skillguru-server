package skillguru.service.zencal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import skillguru.exception.ApiException;
import skillguru.model.request.zencal.ZencalProfileCreateRequest;
import skillguru.model.response.ZencalProfileResponse;
import skillguru.service.MentorService;

import java.util.Collections;

@Service
public class ZencalProfileService {
    private static final String API_BASE_URL = "https://app.zencal.io/api/v1";
    private static final String REGISTER_ENDPOINT = "/profile/register";
    private static final String PROFILE_ENDPOINT = "/profile";
    private static final String API_KEY_HEADER = "X-Zencal-Api-Key";
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    private final MentorService mentorService;
    @Value("${zencal.api.key}")
    private String apiKey;

    public ZencalProfileService(@Value("${zencal.api.key}") String apiKey, MentorService mentorService) {
        this.mentorService = mentorService;
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        this.headers.set("X-Zencal-Api-Key", apiKey);
    }

    public void createProfile(ZencalProfileCreateRequest request) throws ApiException {
        String url = API_BASE_URL + REGISTER_ENDPOINT;

        HttpEntity<ZencalProfileCreateRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<ZencalProfileCreateRequest> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ZencalProfileCreateRequest.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            mentorService.createProfile(responseEntity.getBody());

        } else {
            throw new ApiException("Błąd podczas rejestracji użytkownika.");
        }
    }

    public ZencalProfileResponse updateProfile() {
        String url = API_BASE_URL + PROFILE_ENDPOINT;

        headers.set(API_KEY_HEADER, apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ZencalProfileResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ZencalProfileResponse.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {

                return MentorService.updateProfile(responseEntity.getBody());
            } else {
                throw new ApiException("Błąd podczas pobierania profilu użytkownika");
            }
        } catch (Exception e) {
            throw new ApiException("Wystąpił błąd");
        }
    }

    public ZencalProfileResponse getUserProfile() {
        String url = API_BASE_URL + PROFILE_ENDPOINT;

        headers.set(API_KEY_HEADER, apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ZencalProfileResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ZencalProfileResponse.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                MentorService.getUserProfile(responseEntity.getBody());

                return responseEntity.getBody();
            } else {
                throw new ApiException("Błąd podczas pobierania profilu użytkownika.");
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new ApiException("Wystąpił wyjątek podczas wywoływania API.");
        }
    }
}