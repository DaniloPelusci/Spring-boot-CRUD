package br.com.danilopelusci.modelagemc;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.danilopelusci.modelagemc.domain.Categoria;
import br.com.danilopelusci.modelagemc.domain.Cidade;
import br.com.danilopelusci.modelagemc.domain.Estado;
import br.com.danilopelusci.modelagemc.domain.Produto;
import br.com.danilopelusci.modelagemc.repositories.CategoriaRepository;
import br.com.danilopelusci.modelagemc.repositories.CidadeRepository;
import br.com.danilopelusci.modelagemc.repositories.EstadoRepository;
import br.com.danilopelusci.modelagemc.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
			
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		//------------------
		
		Estado est1 = new Estado(null,"Minas Gerais");
	    Estado est2 = new Estado(null, "Sao Paulo");
	    
	    Cidade c1 = new Cidade(null, "uberlandia", est1);
	    Cidade c2 = new Cidade(null, "Sao Paulo", est2);
	    Cidade c3 = new Cidade(null, "Campinas", est2);
	    
	    est1.getCidades().addAll(Arrays.asList(c1));
	    est2.getCidades().addAll(Arrays.asList(c2,c3));
	    
	    estadoRepository.saveAll(Arrays.asList(est1, est2));
	    cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	    
	    
	    
	    
	    
		
	}
	

}
