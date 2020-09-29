package br.com.danilopelusci.modelagemc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.danilopelusci.modelagemc.domain.Cliente;

public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "o tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private Integer id;
	public ClienteDTO() {
		super();
	}
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email= obj.getEmail();
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	 
	
	
}	
