package com.example.todo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository repo;

    public TodoController(TodoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Todo> all() { return repo.findAll(); }

    @PostMapping
    public Todo add(@RequestBody Todo todo) { return repo.save(todo); }

    @PutMapping("/{id}")
    public Todo toggle(@PathVariable Long id) {
        Todo t = repo.findById(id).orElseThrow();
        t.setDone(!t.isDone());
        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
