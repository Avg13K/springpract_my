package com.example.springpract.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.springpract.models.Professor;
import com.example.springpract.repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExcelService {
    @Autowired
    private ProfessorRepository repo;

    public List<Professor> listAll() {
        return repo.findAll(Sort.by("id").ascending());
    }
}
