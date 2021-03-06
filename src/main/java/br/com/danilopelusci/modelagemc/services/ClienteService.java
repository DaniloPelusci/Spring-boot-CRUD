package br.com.danilopelusci.modelagemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danilopelusci.modelagemc.domain.Cidade;
import br.com.danilopelusci.modelagemc.domain.Cliente;
import br.com.danilopelusci.modelagemc.domain.Endereco;
import br.com.danilopelusci.modelagemc.domain.enums.Perfil;
import br.com.danilopelusci.modelagemc.domain.enums.TipoCliente;
import br.com.danilopelusci.modelagemc.dto.ClienteDTO;
import br.com.danilopelusci.modelagemc.dto.ClienteNewDTO;
import br.com.danilopelusci.modelagemc.repositories.ClienteRepository;
import br.com.danilopelusci.modelagemc.repositories.EnderecoRepository;
import br.com.danilopelusci.modelagemc.security.UserSS;
import br.com.danilopelusci.modelagemc.services.exceptions.AuthorizationException;
import br.com.danilopelusci.modelagemc.services.exceptions.DataIntegrityException;
import br.com.danilopelusci.modelagemc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	
	
	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN)&& !id.equals(user.getid())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	
	public Cliente update(Cliente obj) {
		
		Cliente newObj = find(obj.getId());
		updateData( newObj , obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {		
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possivel excluir um cliente Pois tem entidades relacionadas");
			
		}
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =   PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
		
	}
	 public Cliente fromDTO(ClienteDTO objDTO) {
		 return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null, null, null);
	 }
	 
	 public Cliente fromDTO(ClienteNewDTO objDTO) {
		 Cliente cli = new Cliente(null, objDTO.getNome(),objDTO.getEmail() , objDTO.getCpfOUCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		 Cidade cid =  new Cidade(objDTO.getCidadeId(), null, null);
		 Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
		
		 
		
	 }
	 
	 private void updateData(Cliente newObj, Cliente obj) {
		 newObj.setNome(obj.getNome());
		 newObj.setEmail(obj.getEmail());
	 }

}
