package br.com.danilopelusci.modelagemc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.danilopelusci.modelagemc.services.DBService;
import br.com.danilopelusci.modelagemc.services.EmailService;
import br.com.danilopelusci.modelagemc.services.SmtpEmailService;

@Configuration
@Profile( "dev")
public class DevConfig {
	@Autowired 
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instatiateTesteDatabase();
		
		return true;
	}
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	/*
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}*/

}
