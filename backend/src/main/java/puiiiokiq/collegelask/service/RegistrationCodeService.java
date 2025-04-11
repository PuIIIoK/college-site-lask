package puiiiokiq.collegelask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puiiiokiq.collegelask.model.RegistrationCode;
import puiiiokiq.collegelask.repository.RegistrationCodeRepository;

@Service
public class RegistrationCodeService {

    @Autowired
    private RegistrationCodeRepository registrationCodeRepository;

    // Создание и сохранение одноразового кода
    public RegistrationCode createRegistrationCode(String code) {
        RegistrationCode registrationCode = new RegistrationCode();
        registrationCode.setCode(code);
        registrationCode.setUsed(false);  // Ключ еще не использован
        return registrationCodeRepository.save(registrationCode);
    }

    public String isValidRegistrationCode(String registrationCode) {
        // Ищем регистрационный код в базе данных
        RegistrationCode code = registrationCodeRepository.findByCode(registrationCode);

        // Проверяем, существует ли код и не был ли он использован
        if (code == null) {
            return "Код регистрации не найден.";
        } else if (code.isUsed()) {
            return "Код уже был использован.";
        } else {
            return "Код действителен.";
        }
    }

}
