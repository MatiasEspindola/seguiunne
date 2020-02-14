/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.config;

/**
 *
 * @author Ander
 */
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.iterart.seguiunne.auth.LoginSuccessHandler;
//import net.iterart.seguiunne.service.JpaUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	LoginSuccessHandler lsh;
	
	@Autowired
	DataSource ds;
	
//	@Autowired
//	private JpaUserDetailsService userDetailsService;
	
//	@Autowired
//	PasswordEncoder passEncoder;
	
	@Autowired
	public void ConfigurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		
//		build.userDetailsService(userDetailsService)
//		.passwordEncoder(passEncoder);
		
		
		//Autorizacion con JDBC
		
		build.jdbcAuthentication()
		.dataSource(ds)
		//.passwordEncoder(passEncoder)
		.usersByUsernameQuery("select usuario, salt, hab from Usuarios where usuario =?")
		.authoritiesByUsernameQuery("select u.usuario, a.rol from Roles a inner join Usuarios u on u.fk_id_rol=a.pk_id_rol where u.usuario=?");
		
		///Autorizacion en memoria
		
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
//		
//		build.inMemoryAuthentication()
//		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
//		.withUser(users.username("user").password("user").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
//		.antMatchers("/proyectos/listar/**").hasAnyRole("Usuario")
//		.antMatchers("/uploads/**").hasAnyRole("Usuario")
//		.antMatchers("/actividades/**").hasAnyRole("Usuario")
//		.antMatchers("/proyectos/**").hasAnyRole("Usuario")
//		.antMatchers("/tareas/**").hasAnyRole("Usuario")
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.successHandler(lsh)
			.loginPage("/login")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}
}
