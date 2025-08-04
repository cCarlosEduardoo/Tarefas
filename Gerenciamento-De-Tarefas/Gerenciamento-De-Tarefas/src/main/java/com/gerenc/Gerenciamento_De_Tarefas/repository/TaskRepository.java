package com.gerenc.Gerenciamento_De_Tarefas.repository;

import com.gerenc.Gerenciamento_De_Tarefas.model.Status;
import com.gerenc.Gerenciamento_De_Tarefas.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByTaskStatus(Status status);
}
