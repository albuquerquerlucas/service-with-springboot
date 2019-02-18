package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.PessoaModel;

public interface PessoaRepository extends Repository<PessoaModel, Long> {
	
	void save(PessoaModel pessoa);
	
	void delete(PessoaModel pessoa);
	
	List<PessoaModel> findAll();
	
	PessoaModel findByCodigo(Long codigo);
}
