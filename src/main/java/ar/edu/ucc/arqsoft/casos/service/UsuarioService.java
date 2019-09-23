package ar.edu.ucc.arqsoft.casos.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqsoft.casos.dao.DaoGenerico;
import ar.edu.ucc.arqsoft.casos.dto.UsuarioDto;
import ar.edu.ucc.arqsoft.casos.model.Usuario;

@Service
@Transactional
public class UsuarioService {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	DaoGenerico<Usuario, Long> usuarioDao;
	
	public void grabarUsuario(UsuarioDto dto){
		// Creo un producto del modelo nuevo.
		Usuario usuario = new Usuario();
		
		// Setarle los valores que vienen del dto
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		
		// Grabo el producto
		usuarioDao.saveOrUpdate(usuario);
		
		log.info("Se grabó el producto: " + usuario.getId());
		
		// Le seto el id en el dto
		dto.setId(usuario.getId());
	}
	
	
	public void actualizarUsuario(UsuarioDto dto){
		// Levanto el producto a actualizar desde la DB.
		Usuario usuario = usuarioDao.load(dto.getId());
		
		// Seteo los valores a actualizar
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		
		// Actualizo en la db
		usuarioDao.update(usuario);
		
		log.info("Se actualizo el producto: " + dto.getId());
	}

	public UsuarioDto getUsuarioById(Long id) {
		// Load del producto
		Usuario usuario = usuarioDao.load(id);
		
		//Nuevo Prducto DTO
		UsuarioDto dto = new UsuarioDto();
		
		// Setar Valores.
		dto.setApellido(usuario.getApellido());
		dto.setNombre(usuario.getNombre());
		dto.setId(usuario.getId());
		
		return dto;
	}
	
	public List<UsuarioDto> getAll(){
		List<Usuario> usuarios;
		usuarios = usuarioDao.getAll();
		
		List<UsuarioDto> usuarioDtos = new ArrayList<UsuarioDto>();
		
		for (Usuario u : usuarios){
			usuarioDtos.add(new UsuarioDto(u.getId(), u.getNombre(), u.getApellido()));
		}
		
		
		return usuarioDtos;
		
	}


}
