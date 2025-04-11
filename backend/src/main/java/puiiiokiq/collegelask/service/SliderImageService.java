package puiiiokiq.collegelask.service;

import org.springframework.stereotype.Service;
import puiiiokiq.collegelask.model.SliderImage;
import puiiiokiq.collegelask.repository.SliderImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SliderImageService {

    private final SliderImageRepository sliderImageRepository;

    public SliderImageService(SliderImageRepository sliderImageRepository) {
        this.sliderImageRepository = sliderImageRepository;
    }

    public List<SliderImage> getAllSliderImages() {
        return sliderImageRepository.findAll();
    }

    public SliderImage getSliderImageById(Long id) {
        Optional<SliderImage> optional = sliderImageRepository.findById(id);
        return optional.orElse(null);
    }
}
