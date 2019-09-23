package ar.edu.ucc.arqsoft.casos.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqsoft.casos.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/app-context.xml",
		"file:src/main/resources/hibernate-context.xml" })
public class UsuarioDaoTest {

	@Autowired
	DaoGenerico<Usuario, Long> usuarioDao;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	Usuario usuarioTest;

	// Se ejecuta siempre antes de ejecutarse cada uno de los métodos de test.
	@Before
	public void init() {
		logger.info("Inicializando Usuarios de Test para el DAO");
		
		usuarioTest = new Usuario();
		usuarioTest.setApellido("Apellido Test");
		usuarioTest.setNombre("Nombre Test");
		
	}
	
	
	@Transactional
	@Test
	public void insertUsuarioTest(){
		logger.info("Testing insert Usuario");
		
		usuarioDao.insert(usuarioTest);
		Assert.assertNotNull(usuarioTest.getId());
	}
	
	@Transactional
	@Test
	public void getAllUsuarioTest(){
		logger.info("Testing Update Usuario");
		
		usuarioDao.insert(usuarioTest);
		List<Usuario> usuarios = usuarioDao.getAll();
			
		Assert.assertEquals(1, usuarios.size());
	}
}
