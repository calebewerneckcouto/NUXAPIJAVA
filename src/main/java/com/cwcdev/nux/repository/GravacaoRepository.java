package com.cwcdev.nux.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwcdev.nux.model.Gravacao;

public interface GravacaoRepository extends JpaRepository<Gravacao, Long> {
	java.util.List<Gravacao> findByNumeroSlot(int numeroSlot);
	
	
	

	
	@Query("SELECT g FROM Gravacao g WHERE " +
		       "(:tema IS NULL OR :tema = '' OR LOWER(g.tema) LIKE LOWER(CONCAT('%', :tema, '%')))")
		Page<Gravacao> buscarPorTema(
		    @Param("tema") String tema,
		    Pageable pageable);
}

