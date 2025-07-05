package com.cwcdev.nux.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // ✅ CERTO
import org.springframework.stereotype.Service;

import com.cwcdev.nux.model.Gravacao;
import com.cwcdev.nux.repository.GravacaoRepository;

@Service
public class GravacaoService {

	@Autowired
	private GravacaoRepository repository;

	public Gravacao salvar(Gravacao g) {
		return repository.save(g);
	}

	public Page<Gravacao> listarPaginado(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Gravacao> buscarPorTema(String tema, Pageable pageable) {
	    String filtroTema = (tema != null && !tema.trim().isEmpty()) ? tema.trim() : "";
	    return repository.buscarPorTema(filtroTema, pageable);
	}



	public List<Gravacao> listarTodos() {
		return repository.findAll();
	}

	public Optional<Gravacao> buscarPorSlot(int slot) {
		return repository.findByNumeroSlot(slot).stream().findFirst();
	}

	public Optional<Gravacao> buscarPorId(Long id) {
		return repository.findById(id);
	}

	public Gravacao editar(Long id, Gravacao novaGravacao) {
		return repository.findById(id).map(g -> {
			g.setNumeroSlot(novaGravacao.getNumeroSlot());
			g.setTema(novaGravacao.getTema());
			g.setAssunto(novaGravacao.getAssunto());
			return repository.save(g);
		}).orElseThrow(() -> new RuntimeException("Gravação não encontrada"));
	}

	public void excluir(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Gravação não encontrada para exclusão");
		}
		repository.deleteById(id);
	}
}