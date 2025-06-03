package com.rafael.clients.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.clients.domain.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    boolean existsByDddAndMainNumber(String ddd, String mainNumber);


    /**
     * Verifica se existe um telefone com o mesmo DDD e número principal,
     * excluindo os telefones que pertencem ao cliente especificado.
     * 
     * @param ddd DDD do telefone a ser verificado
     * @param mainNumber Número principal do telefone a ser verificado  
     * @param id ID do telefone que deve ser excluído da verificação
     * @return true se existe outro telefone com os mesmos dados, false caso contrário
     */
    boolean existsByDddAndMainNumberAndClientIdNot(String ddd, String mainNumber, UUID id);
    
}
