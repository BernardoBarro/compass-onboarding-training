package br.com.compasso.primeiroProjeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.primeiroProjeto.entity.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByDescricao(String descricao);

}
