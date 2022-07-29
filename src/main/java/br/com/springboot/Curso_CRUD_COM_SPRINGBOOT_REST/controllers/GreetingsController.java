package br.com.springboot.Curso_CRUD_COM_SPRINGBOOT_REST.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.Curso_CRUD_COM_SPRINGBOOT_REST.model.Usuario;
import br.com.springboot.Curso_CRUD_COM_SPRINGBOOT_REST.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	/**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "CURSO SPRING BOOT BEM VINDO " + name + "!";
    }
    
    
    @RequestMapping(value = "/olanumdo/{nome}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    
    	Usuario uso  = new Usuario();
    	uso.setNome(nome);
    	uso.setIdade(42);
    	usuarioRepo.saveAndFlush(uso);
    	return "Ola mundo ;" + nome;
    }
    
    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaTodos()
    	{
    		List<Usuario> usuarios = usuarioRepo.findAll();
    		
    		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna uma lista  JSON */
    	}
    @PostMapping(value = "/salvar")      /*Mapear a url*/
    @ResponseBody						 /*Descrição da resposta */
    public ResponseEntity<Usuario>salvar(@RequestBody Usuario usuario) /*  @RequestBody  recebe o paramentro para salvar*/
    	{
    		Usuario uso = usuarioRepo.saveAndFlush(usuario);
    		
    		return new ResponseEntity<Usuario>(uso,HttpStatus.CREATED);
    	}
    
    @DeleteMapping(value = "/delete")      /*Mapear a url*/
    @ResponseBody						 /*Descrição da resposta */
    public ResponseEntity<String> delete(@RequestParam(name = "id") Long id) /*  @RequestBody  recebe o paramentro para salvar*/
    	{
    		 usuarioRepo.deleteById(id);
    		
    		return new ResponseEntity<String>("Usuário deletado com Sucesso",HttpStatus.OK);
    	}
    
    @GetMapping(value = "/buscarUserId")      /*Mapear a url*/
    @ResponseBody						 /*Descrição da resposta */
    public ResponseEntity<Usuario> buscarPorId(@RequestParam(name = "id") Long id) /*  @RequestBody  recebe o paramentro para salvar*/
    	{
    		Usuario uso=  usuarioRepo.findById(id).get();
    		
    		return new ResponseEntity<Usuario>(uso,HttpStatus.OK);
    	}
    
    @PutMapping(value = "/atualizar")      /*Mapear a url*/
    @ResponseBody						 /*Descrição da resposta */
    public ResponseEntity<?>atualizar(@RequestBody Usuario usuario) /*  @RequestBody  recebe o paramentro para salvar*/
    	{
    		
    		if(usuario.getId() == 0L )
    		{
    			return new ResponseEntity<String>("O id não foi informado",HttpStatus.OK);
    		}
    		Usuario uso = usuarioRepo.saveAndFlush(usuario);
    		
    		return new ResponseEntity<Usuario>(uso,HttpStatus.OK);
    	}
    
    
    @GetMapping(value = "/buscarUserName")      /*Mapear a url*/
    @ResponseBody						 /*Descrição da resposta */
    public ResponseEntity<List<Usuario>> buscarPornome(@RequestParam(name = "nome") String nome) /*  @RequestBody  recebe o paramentro para buscar*/
    	{
    	List<Usuario> ususarios=  usuarioRepo.buscarPornome(nome.trim().toUpperCase());
    		
    		return new ResponseEntity<List<Usuario>>(ususarios,HttpStatus.OK);
    	}
}
