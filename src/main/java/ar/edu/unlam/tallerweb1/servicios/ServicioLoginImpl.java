package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {
	private List<Reserva> reservasCerveceria = new LinkedList<>();
	private RepositorioUsuario repositorioUsuario;
	private ServicioMapa servicioMapa;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao, ServicioMapa servicioMapa){
		this.repositorioUsuario = servicioLoginDao;
		this.servicioMapa = servicioMapa;
	}

	@Override
	public Usuario consultarUsuario (String email, String password) {
		return repositorioUsuario.buscarUsuario(email, password);
	}

	@Override
	public Usuario registrar(String email, String password) throws Exception{
		Usuario buscado = repositorioUsuario.buscar(email);
		if(buscado != null)
			throw new Exception();
		Usuario nuevo = new Usuario();
		nuevo.setEmail(email);
		nuevo.setPassword(password);
		if(laContraseniaEsAdmin(password)){
			nuevo.setRol("admin");
		}else{
			nuevo.setRol("usuario");
		}
		Cuenta cuenta = new Cuenta();
		cuenta.setCreada(new Date());
		nuevo.setCuenta(cuenta);
		repositorioUsuario.guardar(nuevo);
		return nuevo;
	}

	@Override
	public Usuario actualizarPerfil(String barrio,String direccion,Usuario usuario) {
		usuario.setBarrio(barrio);
		usuario.setDireccion(direccion);
		List<Double> coordenadasCerveceria = servicioMapa.buscarCoordenadasPorDireccion(direccion);
		usuario.setLatitud(coordenadasCerveceria.get(0));
		usuario.setLongitud(coordenadasCerveceria.get(1));
		repositorioUsuario.actualizarBarrio(usuario);
		return usuario;
	}

	@Override
	public void actualizar(Usuario usuario, Long id) {
		usuario.setReservas(reservasCerveceria);

		repositorioUsuario.actualizarUsuario(usuario);
	}

	private boolean laContraseniaEsAdmin(String clave) {
		return (clave.equals("admin") ? true : false);
	}


}
