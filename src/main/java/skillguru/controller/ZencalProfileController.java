package skillguru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skillguru.exception.ApiException;
import skillguru.model.request.zencal.ZencalProfileCreateRequest;
import skillguru.model.response.ZencalProfileResponse;
import skillguru.service.zencal.ZencalProfileService;

@RestController
@RequestMapping("/api/profile")
public class ZencalProfileController {

    private final ZencalProfileService zencalProfileService;

    @Autowired
    public ZencalProfileController(ZencalProfileService zencalProfileService) {
        this.zencalProfileService = zencalProfileService;
    }

    @PostMapping("/register-mentor")
    public ResponseEntity<?> createProfile(@RequestBody ZencalProfileCreateRequest request) {
        try {
            zencalProfileService.createProfile(request);
            return ResponseEntity.ok("Rejestracja zakończona pomyślnie.");


        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas rejestracji użytkownika.");
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile() {
        try {
            ZencalProfileResponse profileResponse = zencalProfileService.getUserProfile();
            return ResponseEntity.ok(profileResponse);
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania profilu użytkownika.");
        }
    }
}
