package com.uce.edu.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.repository.modelo.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.persist(persona);
	}

	@Override
	public Persona seleccionarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myQuery = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.cedula =:cedula",
				Persona.class);
		myQuery.setParameter("cedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	public void actualizar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.merge(persona);
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionarPorCedula(cedula));
	}

	@Override
	public List<Persona> consultarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myQuery = this.entityManager.createQuery("SELECT p FROM Persona p", Persona.class);
		return myQuery.getResultList();
	}

}
