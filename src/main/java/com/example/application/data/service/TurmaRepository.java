package com.example.application.data.service;

import com.example.application.data.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TurmaRepository
        extends
            JpaRepository<Turma, Long>,
            JpaSpecificationExecutor<Turma> {

}
