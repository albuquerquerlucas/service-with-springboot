package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.PessoaModel;
import com.example.model.ResponseModel;
import com.example.repository.PessoaRepository;

@RestController
@RequestMapping("/service")
@CrossOrigin("*")
@Service
@Transactional
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * SALVAR UM NOVO REGISTRO
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/pessoa", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody PessoaModel pessoa) {
		
		try {
			
			this.pessoaRepository.save(pessoa);
			
			return new ResponseModel(1, "Registro salvo com sucesso!");
					
		}catch (Exception e) {
			
			return new ResponseModel(0, e.getMessage());
		}
	}
	
	
	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/pessoa", 
			method   = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody PessoaModel pessoa) {
		
		try {
			
			this.pessoaRepository.save(pessoa);
			
			return new ResponseModel(1, "Registro atualizado com sucesso!");
			
		}catch (Exception e) {
			
			return new ResponseModel(0, e.getMessage());
		}
	}
	
	
	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * @return
	 */
	@RequestMapping(value = "/pessoa", 
			method   = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PessoaModel> consultar(){
 
		return this.pessoaRepository.findAll();
	}
	
	
	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/{codigo}",
			method   = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			
	public @ResponseBody PessoaModel buscar(@PathVariable("codigo") Integer codigo) {
		
		return this.pessoaRepository.findByCodigo(Integer.toUnsignedLong(codigo));
	}
	
	
	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value="/pessoa/{codigo}", 
			method   = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo){
 
		PessoaModel pessoaModel = pessoaRepository.findByCodigo(Integer.toUnsignedLong(codigo));
 
		try {
 
			pessoaRepository.delete(pessoaModel);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			
			return new ResponseModel(0, e.getMessage());
		}
	}
}
