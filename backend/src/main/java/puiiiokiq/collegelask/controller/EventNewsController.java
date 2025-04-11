package puiiiokiq.collegelask.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import puiiiokiq.collegelask.model.EventNews;
import puiiiokiq.collegelask.service.EventNewsService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class EventNewsController {

    private final EventNewsService service;

    public EventNewsController(EventNewsService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventNews> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventNews> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EventNews create(@RequestBody EventNews eventNews) {
        return service.create(eventNews);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Новый метод для получения фотографии по ID новости
    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable Long id) {
        // Находим новость по ID
        EventNews eventNews = service.getById(id).orElse(null);

        if (eventNews == null || eventNews.getPhotoName() == null) {
            return ResponseEntity.notFound().build(); // Если новость не найдена или фото нет
        }

        // Получаем имя фотографии из поля photoName
        String photoName = eventNews.getPhotoName();
        // Путь к директории с изображениями
        String IMAGE_DIR = "src/main/resources/static/images/";
        Path photoPath = Paths.get(IMAGE_DIR, photoName);

        if (Files.exists(photoPath)) {
            try {
                Resource resource = new UrlResource(photoPath.toUri());
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf("image/webp"))  // Устанавливаем тип контента для WebP
                        .body(resource);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);  // Обработка исключений
            }
        } else {
            return ResponseEntity.notFound().build();  // Если файл не найден
        }
    }
}
