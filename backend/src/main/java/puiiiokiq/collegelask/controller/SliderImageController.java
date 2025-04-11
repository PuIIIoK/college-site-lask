
package puiiiokiq.collegelask.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import puiiiokiq.collegelask.model.SliderImage;
import puiiiokiq.collegelask.service.SliderImageService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/slider")
public class SliderImageController {

    private final SliderImageService sliderImageService;

    public SliderImageController(SliderImageService sliderImageService) {
        this.sliderImageService = sliderImageService;
    }

    // Для получения всех изображений слайдера
    @GetMapping
    public ResponseEntity<List<SliderImage>> getAllSliderImages() {
        List<SliderImage> sliderImages = sliderImageService.getAllSliderImages();
        if (sliderImages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sliderImages);
    }

    // Для получения одного изображения по ID
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getSliderImage(@PathVariable Long id) {
        SliderImage sliderImage = sliderImageService.getSliderImageById(id);

        if (sliderImage == null) {
            return ResponseEntity.notFound().build();  // Если картинка не найдена
        }

        String imageName = sliderImage.getImageName();
        Path imagePath = Paths.get("src/main/resources/static/images/" + imageName);

        if (Files.exists(imagePath)) {
            try {
                Resource resource = new UrlResource(imagePath.toUri());
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
