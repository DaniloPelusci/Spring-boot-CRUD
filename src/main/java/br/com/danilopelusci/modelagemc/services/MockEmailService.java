package br.com.danilopelusci.modelagemc.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	
	@Override
	public void sendEmail(SimpleMailMessage sm) {
		LOG.info("emulando envio de email...");
		LOG.info(sm.toString());
		LOG.info("email Enviado");
		
		
	}

}
