package com.example.task1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Map<String, User> users = new HashMap<>() {
        {
            put("1", new User("Hassan", "abc@gmail.com"));
            put("2", new User("Khan", "khan@gmail.com"));
            put("3", new User("Bobby", "bobby@gmail.com"));
            

        }
    };

    public Collection<User> get() {
        return users.values();
    }

    public User createUser(String name, String email) {
        String id = String.valueOf(users.size() + 1); // Simple ID generation
        User newUser = new User(name, email);
        users.put(id, newUser);
        return newUser;
    }

}
