package com.example.application.data.service;

import com.example.application.data.entity.Boletim;
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

    public Optional<Boletim> get(Long id) {
        return repository.findById(id);
    }

    public Boletim update(Boletim entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Boletim> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Boletim> list(Pageable pageable, Specification<Boletim> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
