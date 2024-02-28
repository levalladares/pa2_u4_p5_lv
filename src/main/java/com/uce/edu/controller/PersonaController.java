package com.uce.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.repository.modelo.Persona;
import com.uce.edu.service.IPersonaService;

//http://localhost:8080/personas/buscar
@Controller
@RequestMapping("personas")
public class PersonaController {
	@Autowired
	private IPersonaService iPersonaService;

	// path
	// GET
	//
	@GetMapping("/buscarPorCedula/{cedulaPersona}")
	public String buscarPorCedula(@PathVariable("cedulaPersona") String cedula, Model modelo) {
		Persona persona = this.iPersonaService.buscarPorCedula(cedula);
		modelo.addAttribute("persona",persona);
		return "vistaPersona";
	}

	@PutMapping("/actualizar/{cedulaPersona}")
	public String actualizar(@PathVariable("cedulaPersona") String cedula, Persona persona) {
		persona.setCedula(cedula);
		Persona perAux= this.iPersonaService.buscarPorCedula(cedula);
		perAux.setApellido(persona.getApellido());
		perAux.setNombre(persona.getNombre());
		perAux.setCedula(persona.getCedula());
		perAux.setGenero(persona.getGenero());
		this.iPersonaService.actualizar(perAux);
		return "redirect:/personas/buscarTodos";
	}

	@DeleteMapping("/borrarPorCedula/{cedula}")
	public String borrar(@PathVariable("cedula") String cedula) {
		this.iPersonaService.borrarPorCedula(cedula);
		return "redirect:/personas/buscarTodos";
	}

	@PostMapping("/guardar")
	public String guardar() {
		return "";
	}
	
	@GetMapping("/buscarTodos")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.iPersonaService.consultarTodos();
		modelo.addAttribute("personas", lista);
		return"vistaListaPersonas";
	}
	
	@PostMapping("/insertar")
	public String insertar(Persona persona) {
		this.iPersonaService.guardar(persona);
		return "redirect:/personas/buscarTodos";

	}
	
	@GetMapping("/nuevaPersona")
	public String mostrarNuevaPersona(Model modelo,Persona persona) {
		modelo.addAttribute("persona", persona);
		return"vistaNuevaPersona";
	}

}
