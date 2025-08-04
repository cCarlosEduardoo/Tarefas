package com.gerenc.Gerenciamento_De_Tarefas.service;

import com.gerenc.Gerenciamento_De_Tarefas.model.Status;
import com.gerenc.Gerenciamento_De_Tarefas.model.Tasks;
import com.gerenc.Gerenciamento_De_Tarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void create(Tasks tasks){
        tasks.setTaskStatus(Status.PENDENTE);
        taskRepository.save(tasks);
    }

    public void update(Long id,Tasks tasks){
        Tasks tarefasExistentes = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));

        tarefasExistentes.setTitulo(tasks.getTitulo());
        tarefasExistentes.setDescricao(tasks.getDescricao());
        tarefasExistentes.setTaskStatus(tasks.getTaskStatus());
        tarefasExistentes.setDataCriacao(tasks.getDataCriacao());
        taskRepository.save(tarefasExistentes);
    }

    public void delete(Long id){
        taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrado"));
        taskRepository.deleteById(id);
    }

    public void atualizarStatus(Long id){
       Tasks tasksStatus = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Id de tarefa não encontrado"));
       tasksStatus.setTaskStatus(Status.CONCLUIDO);

       taskRepository.save(tasksStatus);
    }

    public List<Tasks> listarConcluidos(){
        return taskRepository.findByTaskStatus(Status.CONCLUIDO);
    }


    public Page<Tasks> paginacaoDados(int page,int size){
        Pageable pageable = PageRequest.of(page,size);

        return taskRepository.findAll(pageable);

    }


}
