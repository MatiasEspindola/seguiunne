/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.auth;

/**
 *
 * @author Ander
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
//import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SessionFlashMapManager sfmm = new SessionFlashMapManager();
		FlashMap fm = new FlashMap();
		
		fm.put("success", "Bienvenido/a " + authentication.getName());
		sfmm.saveOutputFlashMap(fm, request, response);
		
		logger.info("Sesión iniciada por el usuario " + authentication.getName() + " con el permiso de " + authentication.getAuthorities());
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
