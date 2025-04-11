package puiiiokiq.collegelask.service;

import puiiiokiq.collegelask.model.EventNews;
import puiiiokiq.collegelask.repository.EventNewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventNewsService {

    private final EventNewsRepository repository;

    public EventNewsService(EventNewsRepository repository) {
        this.repository = repository;
    }

    public List<EventNews> getAll() {
        return repository.findAll();
    }

    public Optional<EventNews> getById(Long id) {
        return repository.findById(id);
    }

    public EventNews create(EventNews eventNews) {
        return repository.save(eventNews);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
