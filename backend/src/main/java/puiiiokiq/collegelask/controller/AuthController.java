package puiiiokiq.collegelask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import puiiiokiq.collegelask.service.JWTService;
import puiiiokiq.collegelask.model.Student;
import puiiiokiq.collegelask.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final StudentRepository studentRepository; // Репозиторий для работы с данными студентов

    public AuthController(AuthenticationManager authenticationManager, JWTService jwtService, StudentRepository studentRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Student student) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(student);

            // Создаем объект с информацией о пользователе, токене и сообщении
            LoginResponse response = new LoginResponse();
            response.setUsername(student.getUsername());
            response.setToken(token);
            response.setMessage("Авторизирован!");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Пользователь не авторизирован");
        }

        token = token.substring(7); // Убираем "Bearer " из начала токена

        // Проверяем, валиден ли токен
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(401).body("Пользователь не авторизирован");
        }

        String username = jwtService.getUsernameFromToken(token);

        // Возвращаем информацию о пользователе, если токен валиден
        return ResponseEntity.ok("Пользователь " + username + " авторизован!");
    }

    // Новый эндпоинт для получения профиля пользователя
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Отсутствует токен");
        }

        token = token.substring(7); // удаляем "Bearer "

        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(401).body("Невалидный токен");
        }

        String username = jwtService.getUsernameFromToken(token);
        Optional<Student> studentOpt = studentRepository.findByUsername(username);

        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Пользователь не найден");
        }

        return ResponseEntity.ok(studentOpt.get());
    }


    // Класс для структуры ответа при логине
    public static class LoginResponse {
        private String username;
        private String token;
        private String message;

        // Getters и Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
