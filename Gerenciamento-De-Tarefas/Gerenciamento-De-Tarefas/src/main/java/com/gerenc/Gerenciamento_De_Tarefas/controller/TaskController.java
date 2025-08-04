package com.gerenc.Gerenciamento_De_Tarefas.controller;

import com.gerenc.Gerenciamento_De_Tarefas.model.Status;
import com.gerenc.Gerenciamento_De_Tarefas.model.Tasks;
import com.gerenc.Gerenciamento_De_Tarefas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public void create(@RequestBody Tasks tasks) {
        taskService.create(tasks);
    }

    @GetMapping
    public List<Tasks> read() {
        return taskService.read();
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Tasks tasks) {
        taskService.update(id, tasks);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    @PatchMapping("status/{id}")
    public void atualizarStatus(@PathVariable("id") Long id){
        taskService.atualizarStatus(id);
    }

    @GetMapping("/concluidos")
    public List<Tasks> listarConcluido(){
        return taskService.listarConcluidos();
    }


}