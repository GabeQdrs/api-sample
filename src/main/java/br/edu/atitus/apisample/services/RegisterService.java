package br.edu.atitus.apisample.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.repositories.RegisterRepository;

@Service
public class RegisterService {

	private final RegisterRepository repository;

	public RegisterService(RegisterRepository repository) {
		super();
		this.repository = repository;
	}
	
	//save
	public RegisterEntity save(RegisterEntity newRegister) throws Exception {
		
		//Validação de regra de negócio
		if (newRegister.getLatitude() < -90 || newRegister.getLatitude() > 90)
			throw new Exception("Latitude inválida!");
		if (newRegister.getLongitude() < -180 || newRegister.getLongitude() > 180)
			throw new Exception("Longitude inválida!");
		if(newRegister.getUser() == null || newRegister.getUser().getId() == null)
			throw new Exception("Usuário inválido");
		
		
		//Invocar o método da camada repository
		repository.save(newRegister);
		
		
		return newRegister;
	}
	//findById
	public RegisterEntity findById(UUID id) throws Exception {
		RegisterEntity entity = repository.findById(id)
				.orElseThrow(() -> new Exception("Usuário não encontrado com este id"));
		return entity;
	}
	//findAll
	public List<RegisterEntity> findAll() throws Exception {
		List<RegisterEntity> registers = repository.findAll();
		
		return registers;
	}
	//delete
	public void deleteById(UUID id) throws Exception {
		repository.deleteById(id);
	}
	
}
