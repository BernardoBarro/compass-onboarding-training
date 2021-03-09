package br.com.compasso.primeiroProjeto.controller.form;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.compasso.primeiroProjeto.entity.Estoque;
import br.com.compasso.primeiroProjeto.entity.Produto;
import br.com.compasso.primeiroProjeto.repository.EstoqueRepository;

public class ProdutoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5, message = "Deve ter 5 ou mais caracteres")
	private String descricao;

	@NotNull
	@DecimalMin(value = "0.01")
	private BigDecimal valor;

	@NotNull
	@Min(value = 0L)
	private Long quantidade;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Produto converter(EstoqueRepository estoqueRepository) {
		Estoque estoqueSave = new Estoque(quantidade);
		estoqueRepository.save(estoqueSave);
		Long id = estoqueSave.getId();
		Optional<Estoque> optional = estoqueRepository.findById(id);
		Estoque estoque = new Estoque(optional.get());
		return new Produto(descricao, valor, estoque);
	}

}
