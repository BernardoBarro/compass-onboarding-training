package br.com.compasso.primeiroProjeto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long quantidade;
	
	public Estoque() {
	}
	
	public Estoque(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public Estoque(Estoque estoque) {
		this.id = estoque.getId();
		this.quantidade = estoque.getQuantidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
