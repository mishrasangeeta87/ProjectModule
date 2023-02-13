package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.NoteEntity;
import com.example.springtaskmgr2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity,Integer> {
    List<NoteEntity> getNoteEntitiesByTaskId(Integer  id);

    NoteEntity getNoteEntityByTaskAndId(TaskEntity task, Integer noteId);

    int deleteByTaskIdAndId(Integer taskId,Integer id);

}
