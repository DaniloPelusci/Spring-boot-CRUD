package br.com.danilopelusci.modelagemc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.danilopelusci.modelagemc.services.DBService;
import br.com.danilopelusci.modelagemc.services.EmailService;
import br.com.danilopelusci.modelagemc.services.MockEmailService;

@Configuration
@Profile( "test")
public class TestConfig {
	
	@Autowired 
	private DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instatiateTesteDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
