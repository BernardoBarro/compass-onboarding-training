package br.com.compasso.primeiroProjeto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.primeiroProjeto.controller.dto.ProdutoDto;
import br.com.compasso.primeiroProjeto.controller.form.AtualizacaoProdutoForm;
import br.com.compasso.primeiroProjeto.controller.form.ProdutoForm;
import br.com.compasso.primeiroProjeto.entity.Produto;
import br.com.compasso.primeiroProjeto.repository.EstoqueRepository;
import br.com.compasso.primeiroProjeto.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosRepository produtosRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping
	public List<ProdutoDto> lista(String descricao) {
		if (descricao == null) {
			List<Produto> produto = produtosRepository.findAll();
			return ProdutoDto.converter(produto);
		} else {
			List<Produto> produto = produtosRepository.findByDescricao(descricao);
			return ProdutoDto.converter(produto);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> especificar(@PathVariable Long id) {
		Optional<Produto> optional = produtosRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			UriComponentsBuilder uriBuilder) {
		Produto produto = produtoForm.converter(estoqueRepository);
		produtosRepository.save(produto);

		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoProdutoForm form) {
		Optional<Produto> optional = produtosRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = form.atualizar(id, produtosRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtosRepository.findById(id);
		if (optional.isPresent()) {
			produtosRepository.deleteById(id);
			estoqueRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
