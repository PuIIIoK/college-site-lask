package puiiiokiq.collegelask.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import puiiiokiq.collegelask.model.Student;
import puiiiokiq.collegelask.repository.StudentRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public CustomUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(student.getUsername())
                .password(student.getPassword()) // Пароль будет передан без изменения, так что стоит хешировать его при сохранении в БД
                .authorities("USER") // Здесь могут быть роли/права пользователя
                .build();
    }
}
