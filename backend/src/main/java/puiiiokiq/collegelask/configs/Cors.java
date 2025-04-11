package puiiiokiq.collegelask.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class Cors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // разрешить для всех URL
                .allowedOrigins("http://localhost:3000") // разреши фронту
                .allowedMethods("*") // разреши любые методы (GET, POST и т.д.)
                .allowedHeaders("*"); // разреши любые заголовки
    }
}
