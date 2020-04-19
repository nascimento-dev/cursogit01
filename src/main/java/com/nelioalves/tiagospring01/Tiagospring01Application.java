package com.nelioalves.tiagospring01;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.tiagospring01.domain.Categoria;
import com.nelioalves.tiagospring01.domain.Cidade;
import com.nelioalves.tiagospring01.domain.Cliente;
import com.nelioalves.tiagospring01.domain.Endereco;
import com.nelioalves.tiagospring01.domain.Estado;
import com.nelioalves.tiagospring01.domain.Produto;
import com.nelioalves.tiagospring01.domain.enums.TipoCliente;
import com.nelioalves.tiagospring01.repositories.CategoriaRepository;
import com.nelioalves.tiagospring01.repositories.CidadeRepository;
import com.nelioalves.tiagospring01.repositories.ClienteRepository;
import com.nelioalves.tiagospring01.repositories.EnderecoRepository;
import com.nelioalves.tiagospring01.repositories.EstadoRepository;
import com.nelioalves.tiagospring01.repositories.ProdutoRepository;

@SpringBootApplication
public class Tiagospring01Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Tiagospring01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Ceara");
		Estado est2 = new Estado(null, "Alagoas");
		
		Cidade c1 = new Cidade(null, "Fortaleza", est1);
		Cidade c2 = new Cidade(null, "Maceio", est2);
		Cidade c3 = new Cidade(null, "Caucaia", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Tiago Nascimento", "tiago@gmail.com", "02172839345", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Rosa Alice", "alice@gmail.com", "64068579334", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("85985408198", "85989449723"));
		cli2.getTelefones().addAll(Arrays.asList("85989449723"));
		
		Endereco e1 = new Endereco(null, "Rua Gustavo Sampaio", "2229", "Apto 202", "Parquelandia", "60455-001", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Sao Domingos", "4456", "BL 5 - Apto 502", "Itambe", "60000-000", cli2, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
