package com.vinedpillar.simpledailytodoapp.task;

import com.vinedpillar.simpledailytodoapp.member.Member;
import com.vinedpillar.simpledailytodoapp.member.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
