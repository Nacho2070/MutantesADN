package com.parcialProgramacion.service.repository;

import com.parcialProgramacion.model.MutantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MutantRepository extends JpaRepository<MutantEntity,Long> {
}
