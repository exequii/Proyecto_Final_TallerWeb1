package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.hibernate.annotations.CascadeType.ALL;


//En un @Entity no tengo que ponerle constructor porque sino hibernite se rompe
//y en caso de ponerlo, ademas tengo que hacer el constructor vacio.
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String rol;
	private Boolean activo = false;
	private String barrio;
	private String direccion;

	private Double latitud;
	private Double longitud;

	//lista de reservas
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(value = ALL)
	private List<Reserva> reservasCerveceria = new LinkedList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Cuenta cuenta;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(value = ALL)
	private List<Notificacion> notificaciones = new LinkedList<>();

	/**************************************************************************************************/
	@Override
	public String toString() {
		return "Usuario{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", rol='" + rol + '\'' +
				", activo=" + activo +
				", barrio='" + barrio + '\'' +
				", direccion='" + direccion + '\'' +
				'}';
	}

	/**************************************************************************************************/

	public Usuario() {}

	public Usuario(String email, String password) {
		this.email=email;
		this.password=password;
	}

	public Usuario (String email, String password, String rol, Boolean activo , Cuenta cuenta){
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
		this.cuenta = cuenta;
	}

	public Usuario (String email, String password, String rol, Boolean activo , Cuenta cuenta, List<Reserva> reservasCerveceria){
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
		this.cuenta = cuenta;
		this.reservasCerveceria = reservasCerveceria;
		//this.cerveceriasPropias = cerveceriasPropias;
	}

	public Usuario (String email, String password, String rol, Boolean activo , Cuenta cuenta, List<Reserva> reservasCerveceria,String barrio){
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
		this.cuenta = cuenta;
		this.reservasCerveceria = reservasCerveceria;
		this.barrio = barrio;
	}

	public Usuario (String email, String password, String rol, Boolean activo , Cuenta cuenta, List<Reserva> reservasCerveceria,String barrio,String direccion){
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
		this.cuenta = cuenta;
		this.reservasCerveceria = reservasCerveceria;
		this.barrio = barrio;
		this.direccion = direccion;
	}

	/***************************************************************************************/


	/***************************************************************************************/

	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public boolean activo() {
		return activo;
    }
    public void activar() {
		activo = true;
    }
	public List<Reserva> getReservas() {
		return reservasCerveceria;
	}
	public void setReservas(List<Reserva> reservasCerveceria) {
		this.reservasCerveceria = reservasCerveceria;
	}
	public void addReserva(Reserva reservaCerveceria){
		this.reservasCerveceria.add(reservaCerveceria);
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public List<Reserva> getReservasCerveceria() {
		return reservasCerveceria;
	}

	public void setReservasCerveceria(List<Reserva> reservasCerveceria) {
		this.reservasCerveceria = reservasCerveceria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public List<Notificacion> getNotificaciones() {
		Notificacion noti = new Notificacion(true);
		notificaciones.add(noti);
		for (Notificacion notificacion : notificaciones) {
			if (notificacion.getLeida() == false) {
				notificaciones.add(notificacion);
			}
		}
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
}
