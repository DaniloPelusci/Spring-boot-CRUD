package br.com.danilopelusci.modelagemc.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> error = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return error;
	}

	public void addsError(String fieldName, String messagem) {
		error.add(new FieldMessage(fieldName, messagem));
	}
	
}
