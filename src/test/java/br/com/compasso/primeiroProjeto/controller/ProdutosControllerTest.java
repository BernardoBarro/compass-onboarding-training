package br.com.compasso.primeiroProjeto.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.compasso.primeiroProjeto.entity.Estoque;
import br.com.compasso.primeiroProjeto.entity.Produto;
import br.com.compasso.primeiroProjeto.repository.EstoqueRepository;
import br.com.compasso.primeiroProjeto.repository.ProdutosRepository;
import io.restassured.http.ContentType;

@WebMvcTest
class ProdutosControllerTest {

	private String BASE_URL = "/produtos";

	@Autowired
	private ProdutosController produtosController;

	@MockBean
	private ProdutosRepository produtosRepository;

	@MockBean
	private EstoqueRepository estoqueRepository;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtosController);
	}

	@Test
	public void deveriaDevolver200ComDescricao() {
		List<Produto> produtos = produtos();
		Mockito.when(this.produtosRepository.findByDescricao("Suco de uva")).thenReturn(produtos);
		given().accept(ContentType.JSON).when().get(BASE_URL).then().statusCode(HttpStatus.OK.value());

		Produto produto = produtos.get(0);
		assertEquals("Suco de uva", produto.getDescricao());
		assertEquals(new BigDecimal(8.5), produto.getValor());
		assertEquals(50L, produto.getEstoque().getQuantidade());
	}

	public List<Produto> produtos() {
		Estoque estoque = new Estoque(50L);
		Produto produto = new Produto("Suco de uva", new BigDecimal(8.5), estoque);

		Estoque estoque2 = new Estoque(80L);
		Produto produto2 = new Produto("Suco de Laraja", new BigDecimal(6.5), estoque2);

		List<Produto> lista = new ArrayList<>();

		lista.add(produto);
		lista.add(produto2);

		return lista;
	}

}
