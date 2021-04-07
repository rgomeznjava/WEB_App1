package com.home.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:app.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		PasswordEncoder encoder = passwordEncoder();
		//--ok--UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));
		UserBuilder users = User.builder().passwordEncoder(encoder::encode); //obtiene parametro de funcion lambda y se la pasa al metodo, estatico implicito
		
		auth.inMemoryAuthentication().withUser(users.username("admin").password("123").roles("ADMIN","USER"))
										.withUser(users.username("raul").password("123").roles("USER"));
		
		super.configure(auth);
	}*/
	
	
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		//--ok--UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));
		UserBuilder users = User.builder().passwordEncoder(encoder::encode); //obtiene parametro de funcion lambda y se la pasa al metodo, estatico implicito
		
		auth.inMemoryAuthentication().withUser(users.username("admin").password("123").roles("ADMIN","USER"))
									 .withUser(users.username("raul").password("123").roles("GOD"));
	} 
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
			
		//SOLO LOCAL, PROVISIONAL EN DESPLIEGUES QUITAR
		web.debug(true);
		
		
		System.out.println("----> VALOR: " + env.getProperty("VALOR"));
		System.out.println("----> DESACTIVAR_SEGURIDAD_RECURSOS: " + env.getProperty("DESACTIVAR_SEGURIDAD_RECURSOS"));
		
		boolean desactivar =  Boolean.parseBoolean( env.getProperty("DESACTIVAR_SEGURIDAD_RECURSOS"));
		System.out.println("----> DESACTIVAR_SEGURIDAD_RECURSOS: " + desactivar);

		
		if (desactivar) {
			//Desactiva seguridad, todo ??
			web.ignoring().antMatchers("/**");
		}
	
		//No securizar recursos estaticos.
		//web.ignoring().antMatchers(HttpMethod.GET,securityRecursosEstaticos);      
		//log.info("---> WEB_SECURITY_CONFIG WebSecurity: CONFIGURADO ");
	} 
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//probando
		
		//http.csrf().disable();
		http.authorizeRequests().antMatchers("/","/inicio").permitAll()
		.antMatchers("/pantalla1").hasRole("GOD").and().httpBasic().and().formLogin();
		//.anyRequest().authenticated();
		//http.httpBasic().disable();

		//http.authorizeRequests().antMatchers("/","/inicio","css/**","js/**","images/**").permitAll();
		//super.configure(http);
	} 
	
}

