package com.example.application.data.service;

import com.example.application.data.entity.Turma;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public Optional<Turma> get(Long id) {
        return repository.findById(id);
    }

    public Turma update(Turma entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Turma> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Turma> list(Pageable pageable, Specification<Turma> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
