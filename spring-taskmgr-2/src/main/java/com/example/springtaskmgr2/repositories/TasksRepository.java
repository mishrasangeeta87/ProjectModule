package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity,Integer> {
    List<TaskEntity> findAllByCompleted(boolean completed);

    @Query("SELECT t FROM tasks t WHERE t.completed=false  AND t.dueDate< CURRENT_DATE ")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

//    @Query("SELECT t from tasks  t where t.title like %?1%")
//    List<TaskEntity> findAllByTitle(String title);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}
