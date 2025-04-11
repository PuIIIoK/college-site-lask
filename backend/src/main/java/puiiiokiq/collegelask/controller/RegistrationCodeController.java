package puiiiokiq.collegelask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puiiiokiq.collegelask.service.RegistrationCodeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/code")
public class RegistrationCodeController {

    private final RegistrationCodeService registrationCodeService;

    public RegistrationCodeController(RegistrationCodeService registrationCodeService) {
        this.registrationCodeService = registrationCodeService;
    }

    // Генерация одноразового кода для регистрации студента
    @PostMapping("/generate")
    public ResponseEntity<String> generateRegistrationCode() {
        // Генерация уникального кода для регистрации
        String registrationCode = UUID.randomUUID().toString();  // Генерация случайного уникального кода
        registrationCodeService.createRegistrationCode(registrationCode);

        // Возвращаем сгенерированный код
        return ResponseEntity.ok("Generated registration code: " + registrationCode);
    }

    @PostMapping("/check-code")
    public ResponseEntity<String> checkRegistrationCode(@RequestBody String registrationCode) {
        String result = registrationCodeService.isValidRegistrationCode(registrationCode);

        switch (result) {
            case "Код действителен.":
                return ResponseEntity.ok(result); // 200 OK

            case "Код уже был использован.":
                return ResponseEntity.status(402).body(result); // 402 - Already used

            case "Код регистрации не найден.":
            default:
                return ResponseEntity.status(401).body("Код недействителен или не найден"); // 401 - Invalid or not found
        }
    }


}
