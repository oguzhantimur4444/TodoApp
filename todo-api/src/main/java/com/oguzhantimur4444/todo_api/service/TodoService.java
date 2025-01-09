package com.oguzhantimur4444.todo_api.service;

import com.oguzhantimur4444.todo_api.model.Todo;
import com.oguzhantimur4444.todo_api.model.User;
import com.oguzhantimur4444.todo_api.model.request.TodoRequest;
import com.oguzhantimur4444.todo_api.repository.TodoRepository;
import com.oguzhantimur4444.todo_api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoService(JwtService jwtService, UserRepository userRepository, TodoRepository todoRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername(); // Kullanıcı adını döndürür
            } else {
                return principal.toString(); // Principal bir String ise (örn. JWT token içinde sadece bir ID taşınıyorsa)
            }
        }
        return null; // Kullanıcı kimlik doğrulaması yapılmamış
    }

    public List<Todo> getTodos(){
        User user = userRepository.findByUsername(getCurrentUsername()).orElseThrow();
        return user.getTodos();
    }

    public Todo addTodoByToken(TodoRequest request) {
        Todo todo = new Todo();
        todo.setDescription(request.getDescription());
        todo.setCompleted(false);
        todo.setUser(userRepository.findByUsername(getCurrentUsername()).orElseThrow());

        return todoRepository.save(todo);
    }
}
