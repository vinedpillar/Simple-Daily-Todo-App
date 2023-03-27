package com.vinedpillar.simpledailytodoapp.task;

import com.vinedpillar.simpledailytodoapp.exception.BusinessLogicException;
import com.vinedpillar.simpledailytodoapp.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Task 생성
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    // Task 조회
    // 검색 기능이 필요한가?
    public Task findTask(Long taskId) {

        return findVerifiedTask(taskId);
    }

    // Task 전체 조회
    public Page<Task> findAllTasks(int page, int size) {

        return taskRepository.findAll(PageRequest.of(page, size,
                Sort.by("taskId").descending()));
    }

    // Task 수정
    public Task updateTask(Task task) {
        Task foundTask = findVerifiedTask(task.getId());

        Optional.ofNullable(task.getContent())
                .ifPresent(content -> foundTask.setContent(content));

        return taskRepository.save(foundTask);
    }

    // Task 삭제
    public void deleteTask(Long taskId) {
        Task foundTask = findVerifiedTask(taskId);

        taskRepository.delete(foundTask);
    }

    // Task 검증
    public Task findVerifiedTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task foundTask = optionalTask.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));

        return foundTask;
    }
}
