package com.letscode.contamicroservice.repositories;

import com.letscode.contamicroservice.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    boolean existsByNumero(int numero);
}
