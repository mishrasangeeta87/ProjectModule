package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<TaskEntity,Integer> {
}
