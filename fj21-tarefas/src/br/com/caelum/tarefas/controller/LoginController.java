package br.com.caelum.tarefas.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.controller.dao.JdbcUsuarioDAO;

@Controller
public class LoginController {
	
	private final JdbcUsuarioDAO dao;
	
	@Autowired
	public LoginController(JdbcUsuarioDAO dao)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		this.dao = dao;
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "formulario-login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
	  if(dao.existeUsuario(usuario)) {
	    session.setAttribute("usuarioLogado", usuario);
	    return "menu";
	  }
	  return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
	  session.invalidate();
	  return "redirect:loginForm";
	}

}
