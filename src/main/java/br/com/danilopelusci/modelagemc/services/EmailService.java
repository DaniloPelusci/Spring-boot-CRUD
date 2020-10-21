package br.com.danilopelusci.modelagemc.services;



import org.springframework.mail.SimpleMailMessage;

import br.com.danilopelusci.modelagemc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmatilEmail(Pedido obj);
	void sendEmail(SimpleMailMessage sm);

}
