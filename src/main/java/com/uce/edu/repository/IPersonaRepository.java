package com.uce.edu.repository;

import java.util.List;

import com.uce.edu.repository.modelo.Persona;

public interface IPersonaRepository {

	public void insertar(Persona persona);

	public Persona seleccionarPorCedula(String cedula);

	public void actualizar(Persona persona);

	public void eliminarPorCedula(String cedula);
	
	public List<Persona> consultarTodos();
}
