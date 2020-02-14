/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.controller;

/**
 *
 * @author Ander
 */
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, 
			Model m, Principal p,
			RedirectAttributes flash) {

		m.addAttribute("titulo", "Iniciar Sesión");

		if (p != null) {
			flash.addFlashAttribute("info", "Sesión ya iniciada.");
			return "redirect:/";
		}

		if (error != null)
			m.addAttribute("error", "Usuario o Contraseña incorrectos...");
		
		if (logout != null)
			m.addAttribute("success", "Sesión cerrada...");
			
		return "login";
	}
}
