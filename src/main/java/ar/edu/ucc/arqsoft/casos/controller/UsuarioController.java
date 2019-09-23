package ar.edu.ucc.arqsoft.casos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.ucc.arqsoft.casos.dto.UsuarioDto;
import ar.edu.ucc.arqsoft.casos.model.Usuario;
import ar.edu.ucc.arqsoft.casos.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/usuario", 
	method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDto dto) throws Exception {

		usuarioService.grabarUsuario(dto);

		return new ResponseEntity(dto, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/usuario", method = RequestMethod.PUT, 
	produces = "application/json")
	public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDto dto) throws Exception {

		usuarioService.actualizarUsuario(dto);

		return new ResponseEntity(dto, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/usuario/{usuarioId}", 
	method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getUsuario(@PathVariable("usuarioId") Long id) throws Exception {

		UsuarioDto dto = usuarioService.getUsuarioById(id);

		return new ResponseEntity(dto, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/usuario/all", 
	method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getUsuarios() throws Exception {

		List<UsuarioDto> usuarios = usuarioService.getAll();

		return new ResponseEntity(usuarios, HttpStatus.OK);
	}

}
