package br.com.caelum.tarefas.controller;

import java.sql.SQLException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.controller.dao.JdbcTarefaDAO;

@Controller
public class TarefasController {

	private final JdbcTarefaDAO dao;

	@Autowired
	public TarefasController(JdbcTarefaDAO dao)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		this.dao = dao;
	}

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		model.addAttribute("tarefas", dao.getLista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		dao.remove(tarefa);
		return "forward:listaTarefas";
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/finalizada";
	}

}
