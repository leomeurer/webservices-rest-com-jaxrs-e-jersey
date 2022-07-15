package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoDAO {
	
	private static Map<Long, Carrinho> banco = new HashMap<Long, Carrinho>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Produto videogame = new Produto(6237, "Videogame 4", 4000, 1);
		Produto esporte = new Produto(3467, "Jogo de esporte", 60, 2);
		Carrinho carrinho = new Carrinho()
								.adiciona(videogame)
								.adiciona(esporte)
								.para("Rua Vergueiro 3185, 8 andar", "São Paulo")
								.setId(1l);
		banco.put(1l, carrinho);
		
		Produto videogame2 = new Produto(7897, "Videogame 6", 500, 3);
		Produto esporte2 = new Produto(4356, "Jogo da velha", 300, 4);
		Carrinho carrinho2 = new Carrinho()
								.adiciona(videogame2)
								.adiciona(esporte2)
								.para("Rua Carlos Andrade", "Paraná")
								.setId(2l);
		long id = contador.incrementAndGet();
		banco.put(id, carrinho2);
		
		
	}
	
	public void adiciona(Carrinho carrinho) {
		long id = contador.incrementAndGet();
		carrinho.setId(id);
		banco.put(id, carrinho);
	}
	
	public Carrinho busca(Long id) {
		return banco.get(id);
	}
	
	public Carrinho remove(long id) {
		return banco.remove(id);
	}

}
