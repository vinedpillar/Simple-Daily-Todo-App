package com.vinedpillar.simpledailytodoapp.task;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    // 태스크 포스트 -> 태스크
    Task taskPostToTask(TaskDto.Post requestBody);

    // 테스크 패치 -> 태스크
    Task taskPatchToTask(TaskDto.Patch requestBody);

    // 태스크 -> 태스크 응답
    TaskDto.Response taskToTaskResponse(Task task);

    // 태스크들 -> 태스크 응답 리스트
    List<TaskDto.Response> taskToTaskResponseList(List<Task> tasks);
}
