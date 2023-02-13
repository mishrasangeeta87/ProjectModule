package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity,Integer> {

    Optional<TaskEntity> deleteTaskEntityById(Integer id);

    Optional<TaskEntity> findById(Integer id);
    Optional<List<TaskEntity>> findAllByCompleted(boolean completed);

    Optional<List<TaskEntity>> findAllByTitleEqualsIgnoreCase(String title);

    @Query("SELECT t FROM tasks t WHERE t.completed=false  AND t.dueDate< CURRENT_DATE ")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

//    @Query("SELECT t from tasks  t where t.title like %?1%")
//    List<TaskEntity> findAllByTitle(String title);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}
