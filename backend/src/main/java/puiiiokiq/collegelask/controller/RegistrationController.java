package puiiiokiq.collegelask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puiiiokiq.collegelask.service.RegistrationService;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // DTO-класс для регистрации
    public static class RegisterRequest {
        public String username;
        public String password;
        public String registrationCode;
    }

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody RegisterRequest request) {
        try {
            String token = registrationService.registerStudent(
                    request.username,
                    request.password,
                    request.registrationCode
            );
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
