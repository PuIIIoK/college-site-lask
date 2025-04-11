package puiiiokiq.collegelask.service;

import puiiiokiq.collegelask.model.Application;
import puiiiokiq.collegelask.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public boolean isApplicationExist(String email, String phone) {
        return applicationRepository.existsByEmailAndPhone(email, phone);
    }

    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }
}