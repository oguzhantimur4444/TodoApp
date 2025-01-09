package com.oguzhantimur4444.todo_api.repository;

import com.oguzhantimur4444.todo_api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
