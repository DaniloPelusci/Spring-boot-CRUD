package br.com.danilopelusci.modelagemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.danilopelusci.modelagemc.domain.Categoria;
import br.com.danilopelusci.modelagemc.domain.Cidade;
import br.com.danilopelusci.modelagemc.domain.Cliente;
import br.com.danilopelusci.modelagemc.domain.Endereco;
import br.com.danilopelusci.modelagemc.domain.Estado;
import br.com.danilopelusci.modelagemc.domain.ItemPedido;
import br.com.danilopelusci.modelagemc.domain.Pagamento;
import br.com.danilopelusci.modelagemc.domain.PagamentoComBoleto;
import br.com.danilopelusci.modelagemc.domain.PagamentoComCartao;
import br.com.danilopelusci.modelagemc.domain.Pedido;
import br.com.danilopelusci.modelagemc.domain.Produto;
import br.com.danilopelusci.modelagemc.domain.enums.EstadoPagamento;
import br.com.danilopelusci.modelagemc.domain.enums.TipoCliente;
import br.com.danilopelusci.modelagemc.repositories.CategoriaRepository;
import br.com.danilopelusci.modelagemc.repositories.CidadeRepository;
import br.com.danilopelusci.modelagemc.repositories.ClienteRepository;
import br.com.danilopelusci.modelagemc.repositories.EnderecoRepository;
import br.com.danilopelusci.modelagemc.repositories.EstadoRepository;
import br.com.danilopelusci.modelagemc.repositories.ItemPedidoRepository;
import br.com.danilopelusci.modelagemc.repositories.PagamentoRepository;
import br.com.danilopelusci.modelagemc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagaRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		Categoria cat3 = new Categoria(null, "cama mesa");
		Categoria cat4 = new Categoria(null, "Mesa e cama");
		Categoria cat5 = new Categoria(null, "Esporte");
		Categoria cat6 = new Categoria(null, "jardinagem");
		Categoria cat7 = new Categoria(null, "decoração");
		Categoria cat8 = new Categoria(null, "perfumaria");
		Categoria cat9 = new Categoria(null, "gotica nervosa");
		Categoria cat10 = new Categoria(null, "Musica");
		
		Produto p1 = new Produto(null, "computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
			
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4,cat5, cat6,cat7, cat8,cat9, cat10));
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
	    
	    //----------------------------------------------------------------
	    
	    Cliente cli1= new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
	    
	    cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
	    
	    Endereco e1 = new Endereco(null, "rua flores", "300","apto 303", "jardim","38220834", cli1,c1);
	    Endereco e2 = new Endereco(null, "avenida Matos", "105","sala 800", "centro","38220834", cli1,c2);
	    
	    cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	    
	    clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1, e2));
		    
	    
	    //-----------------------------------------------------------------
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    
	    Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	    Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	    
	    Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO , ped1, 6);
	    ped1.setPagamento(pagto1);
	    
	    Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/07/2017 00:00"), null);
	    ped2.setPagamento(pagto2);
	    
	    cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	    
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	    pagaRepository.saveAll(Arrays.asList(pagto1, pagto2));
	    
	    
	    //----------------------------------------------------------------------
	    
	    ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00 , 1, 2000.00);
	    ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00 , 2, 80.00);
	    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00 , 1, 800.00);
	    
	    ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	    ped2.getItens().addAll(Arrays.asList(ip3));
	    
	    p1.getItens().addAll(Arrays.asList(ip1));
	    p2.getItens().addAll(Arrays.asList(ip3));
	    p3.getItens().addAll(Arrays.asList(ip2));
	    
	    itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	    
	    
		
	}
	

}
