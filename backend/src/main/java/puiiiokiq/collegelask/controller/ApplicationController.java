package puiiiokiq.collegelask.controller;

import puiiiokiq.collegelask.model.Application;
import puiiiokiq.collegelask.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/formula")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Словарь для хранения времени последней заявки каждого пользователя
    private Map<String, Long> lastSubmissionTime = new HashMap<>();

    @PostMapping("/apply")
    public ResponseEntity<String> submitApplication(@RequestBody Application application) {
        // Проверка на одинаковые заявки
        if (applicationService.isApplicationExist(application.getEmail(), application.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такая заявка уже существует.");
        }

        // Ограничение по времени
        String userIdentifier = application.getEmail(); // Можно использовать email как уникальный идентификатор
        long currentTime = System.currentTimeMillis();
        Long lastTime = lastSubmissionTime.get(userIdentifier);

        if (lastTime != null && (currentTime - lastTime) < 60000) { // 1 минута
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы подали слишком много заявок. Пожалуйста, подождите.");
        }

        // Обновляем время последней заявки
        lastSubmissionTime.put(userIdentifier, currentTime);

        try {
            applicationService.saveApplication(application);
            return ResponseEntity.ok("Заявление успешно подано.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка. Попробуйте позже.");
        }
    }
}