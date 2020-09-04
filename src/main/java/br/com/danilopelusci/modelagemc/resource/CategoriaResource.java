package br.com.danilopelusci.modelagemc.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopelusci.modelagemc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1= new Categoria(1, "informatica");
		Categoria cat2=new Categoria(2, "escritorio");
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat2);
		lista.add(cat1);
	return lista;	
	}

}
 