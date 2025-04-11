package puiiiokiq.collegelask.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import puiiiokiq.collegelask.model.Student;
import puiiiokiq.collegelask.model.RegistrationCode;
import puiiiokiq.collegelask.repository.StudentRepository;
import puiiiokiq.collegelask.repository.RegistrationCodeRepository;
import puiiiokiq.collegelask.model.Role;

@Service
public class RegistrationService {

    private final StudentRepository studentRepository;

    private final RegistrationCodeRepository registrationCodeRepository;

    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;  // Мы уже получаем этот бин из конфигурации безопасности

    public RegistrationService(StudentRepository studentRepository, RegistrationCodeRepository registrationCodeRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.registrationCodeRepository = registrationCodeRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // Регистрация студента с проверкой кода регистрации
    public String registerStudent(String username, String password, String registrationCode) {
        // Проверка, существует ли код регистрации
        RegistrationCode code = registrationCodeRepository.findByCode(registrationCode);

        if (code == null || code.isUsed()) {
            throw new IllegalArgumentException("Invalid or used registration code");
        }

        // Пометить код как использованный
        code.setUsed(true);
        registrationCodeRepository.save(code);

        // Создать нового студента
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setRegistrationCode(registrationCode);
        student.setRegistered(true);

        // Установить роль по умолчанию
        student.setRole(Role.USER); // ← вот это главное

        // Сохранить студента
        studentRepository.save(student);

        // Вернуть токен
        return jwtService.generateToken(student);
    }
}
