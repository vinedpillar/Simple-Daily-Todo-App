package com.vinedpillar.simpledailytodoapp.task;

import com.vinedpillar.simpledailytodoapp.dto.MultiResponseDto;
import com.vinedpillar.simpledailytodoapp.dto.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private TaskService taskService;
    private TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    // Task 생성 요청
    @PostMapping
    public ResponseEntity postTask(@RequestBody TaskDto.Post requestBody) {
        Task task = taskService.createTask(taskMapper.taskPostToTask(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        taskMapper.taskToTaskResponse(task)), HttpStatus.CREATED);
    }

    // Task 조회 요청
    @GetMapping("/{task-id}")
    public ResponseEntity getTask(@PathVariable("task-id") Long taskId) {
        Task task = taskService.findTask(taskId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        taskMapper.taskToTaskResponse(task)), HttpStatus.OK);
    }

    // 전체 Task 조회 요청
    @GetMapping
    public ResponseEntity getTaskList(@RequestParam int page,
                                      @RequestParam int size) {
        Page<Task> pageTasks = taskService.findAllTasks(page - 1, size);
        List<Task> tasks = pageTasks.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(
                        taskMapper.taskToTaskResponseList(tasks), pageTasks), HttpStatus.OK);
    }

    // Task 수정 요청
    @PatchMapping("/{task-id}")
    public ResponseEntity patchTask(@PathVariable("task-id") Long taskId,
                                    @RequestBody TaskDto.Patch requestBody) {
        Task task = taskService.updateTask(taskMapper.taskPatchToTask(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(taskMapper.taskToTaskResponse(task)), HttpStatus.OK);
    }


    // Task 삭제 요청
    @DeleteMapping("/{task-id}")
    public ResponseEntity deleteTask(@PathVariable("task-id") Long taskId) {
        taskService.deleteTask(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
