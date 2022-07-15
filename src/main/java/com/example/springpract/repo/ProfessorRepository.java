package com.example.springpract.repo;

import com.example.springpract.models.Professor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepository extends CrudRepository<Professor,Long> {

    List<Professor> findAll(Sort email);
}
