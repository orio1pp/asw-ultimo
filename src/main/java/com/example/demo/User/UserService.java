package com.example.demo.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final HackNewsRepository hackNewsRepository;
    private final RestTemplate restTemplate;
    UserService(HackNewsRepository hackNewsRepository, RestTemplateBuilder restTemplateBuilder){
        this.hackNewsRepository = hackNewsRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    public List<User> getUsers() {
        return hackNewsRepository.findAll();
    }

    public User getUser(String username){
        Optional<User> user = hackNewsRepository.findById(username);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
    public void insertUser(String username){
            User user = hackNewsRepository.findUserByUsername(username);
            if(user == null){
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date currentDate = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
                User newUser = new User(
                        username, currentDate, 1, "", 20, 120, 0, false, false
                );
                hackNewsRepository.save(newUser);
            }
    }

    public User modifyUser(User user){
        return hackNewsRepository.save(user);
    }

}
