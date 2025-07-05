package com.cwcdev.nux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwcdev.nux.model.Gravacao;
import com.cwcdev.nux.service.GravacaoService;

@RestController
@RequestMapping("/api/gravacoes")
@CrossOrigin(origins = {"http://localhost:5173", "https://cwc3d.net"})
public class GravacaoController {

    @Autowired
    private GravacaoService service;

    @PostMapping
    public Gravacao salvar(@RequestBody Gravacao gravacao) {
        return service.salvar(gravacao);
    }

    @GetMapping
    public List<Gravacao> listar() {
        return service.listarTodos();
    }
    
    
    @GetMapping("/paginado")
    public Page<Gravacao> listarPaginadoComFiltro(
            @RequestParam(required = false) String tema,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return service.buscarPorTema(tema, pageable);
    }


    @GetMapping("/{slot}")
    public ResponseEntity<Gravacao> buscarPorSlot(@PathVariable int slot) {
        return service.buscarPorSlot(slot)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gravacao> editar(@PathVariable Long id, @RequestBody Gravacao gravacao) {
        try {
            Gravacao atualizada = service.editar(id, gravacao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/search")
    public Page<Gravacao> buscarPorTemaOuAssunto(
            @RequestParam(required = false) String tema,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return service.buscarPorTema(tema, pageable);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
