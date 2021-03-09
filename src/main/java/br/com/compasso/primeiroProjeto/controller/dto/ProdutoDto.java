package br.com.compasso.primeiroProjeto.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.primeiroProjeto.entity.Produto;

public class ProdutoDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Long quantidade;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.quantidade = produto.getEstoque().getQuantidade();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public static List<ProdutoDto> converter(List<Produto> produto) {
		return produto.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

}
