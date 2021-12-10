package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Disponibilidad;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("servicioReserva")
@Transactional
public class ServicioReservaImpl implements ServicioReserva{

    private RepositorioReserva repositorioReserva;
    private RepositorioCerveceria repositorioCerveceria;
    private ServicioCerveceria servicioCerveceria;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioReservaImpl(RepositorioReserva repositorioReserva, RepositorioCerveceria repositorioCerveceria, RepositorioUsuario repositorioUsuario){
        this.repositorioReserva = repositorioReserva;
        this.repositorioCerveceria = repositorioCerveceria;
        this.repositorioUsuario = repositorioUsuario;
    }


    @Override
    public List<Reserva> consultarReservas(){
        return repositorioReserva.buscarReservas();
    }

    @Override
    public List<Reserva> consultarReservasPorUsuario(String email) {
        return repositorioReserva.buscarReservasPorUsuario(email);
    }


    @Override
    public void eliminarReserva(Long id,Usuario usuario,Cerveceria cerveceria) {

        Reserva buscada = repositorioReserva.buscarReservaPorId(id);
        buscada.setActiva(false);
        repositorioReserva.actualizarDatos(buscada);

    }

    @Override
    public void guardarReserva(String cerveceria, Integer cantidadPersonas, Integer horario) throws Exception {
        Reserva nuevo = new Reserva();
        Cerveceria cerveceriaEncontrada = repositorioCerveceria.buscarCerveceriaPorNombre(cerveceria);
        if(cerveceriaEncontrada != null) {
            nuevo.setCerveceria(cerveceriaEncontrada);
            nuevo.setCantidadPersonas(cantidadPersonas);
            nuevo.setHorario(horario);
            cerveceriaEncontrada.addReserva(nuevo);
            repositorioReserva.guardar(nuevo);
        }
        else{
            throw new Exception("La cerveceria indicada no existe.");
        }
    }

    @Override
    public Disponibilidad guardarReserva(String cerveceria, Integer cantidadPersonas, Integer horario, Usuario usuario){
        Reserva nuevo = new Reserva();
        Cerveceria cerveceriaEncontrada = repositorioCerveceria.buscarCerveceriaPorNombre(cerveceria);

        Integer lugaresOcupadosEnEseHorario = filtradoPorHora(cerveceriaEncontrada.getReservas(),horario);
        Integer lugaresDisponiblesEnEseHorario = cerveceriaEncontrada.getCapacidadTotalPorHora() - lugaresOcupadosEnEseHorario;
        Disponibilidad resultadoReserva = validacionesReserva(cerveceriaEncontrada,lugaresDisponiblesEnEseHorario,cantidadPersonas,horario);


            if(resultadoReserva == Disponibilidad.EN_HORARIO){
                usuario.addReserva(nuevo);

                nuevo.setUsuario(usuario);
                nuevo.setCerveceria(cerveceriaEncontrada);
                nuevo.setCantidadPersonas(cantidadPersonas);
                nuevo.setHorario(horario);
                repositorioReserva.guardar(nuevo);

                cerveceriaEncontrada.addReserva(nuevo);
                repositorioCerveceria.actualizar(cerveceriaEncontrada);

                enviarConGMail(usuario.getEmail(),cerveceriaEncontrada,cantidadPersonas,horario);
            }
            return resultadoReserva;

    }


    public Disponibilidad validacionesReserva(Cerveceria cerveceriaEncontrada,Integer disponible,Integer cantidadPersonas,Integer horario){

            if(disponible >= cantidadPersonas)
                return Disponibilidad.EN_HORARIO;
            return sugerirHorariosProximosConDisponibilidad(cerveceriaEncontrada, cantidadPersonas, horario);

    }

    public Integer filtradoPorHora(List<Reserva> reservas,Integer horario){
        List<Reserva> reservasFiltradas = new LinkedList<>();
        Integer lugaresReservadosEnEseHorario = 0;
        for(Reserva reserva : reservas){
            if((reserva.getHorario()).equals(horario)){
                reservasFiltradas.add(reserva);
                lugaresReservadosEnEseHorario = lugaresReservadosEnEseHorario + reserva.getCantidadPersonas();
            }
        }
        return lugaresReservadosEnEseHorario;
    }

    public Disponibilidad sugerirHorariosProximosConDisponibilidad(Cerveceria cerveceriaEncontrada,Integer cantidadPersonas,Integer horario){
        Integer lugaresOcupadosHoraProxima = filtradoPorHora(cerveceriaEncontrada.getReservas(),(horario + 1));
        Integer lugaresDisponiblesEnHoraProxima = cerveceriaEncontrada.getCapacidadTotalPorHora() - lugaresOcupadosHoraProxima;

        if(lugaresDisponiblesEnHoraProxima>=cantidadPersonas){
            return Disponibilidad.HORA_POSTERIOR;
        }
        else{
            Integer lugaresOcupadosHoraAnterior = filtradoPorHora(cerveceriaEncontrada.getReservas(),(horario - 1));
            Integer lugaresDisponiblesEnHoraAnterior = cerveceriaEncontrada.getCapacidadTotalPorHora() - lugaresOcupadosHoraAnterior;
            if (lugaresDisponiblesEnHoraAnterior>=cantidadPersonas){
                return Disponibilidad.HORA_ANTERIOR;
            }
        }
        return Disponibilidad.NO_DISPONIBLE;
    }

    public void enviarConGMail(String email, Cerveceria cerveceria,Integer cantidadPersonas, Integer horario) {
        String remitente = "drawio.887";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "Pasionquemera98");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        String asunto = "Reserva - Only Beer";
        String mensaje = "Le informamos que su reserva en "+cerveceria.getNombre()+" para "+cantidadPersonas+" personas a las "+horario+" hs se realizo correctamente.";

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Pasionquemera98");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }








    /***************************************************************************************************************************/

    public Boolean enviarConGMailTest(String email, Cerveceria cerveceria,Integer cantidadPersonas, Integer horario) {
        String remitente = "drawio.887";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "Pasionquemera98");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        String asunto = "Reserva - Only Beer";
        String mensaje = "Le informamos que su reserva en "+cerveceria.getNombre()+" para "+cantidadPersonas+" personas a las "+horario+" hs se realizo correctamente.";

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Pasionquemera98");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        }
        catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reserva> filtrarReservasActivas(List<Reserva> reservas) {
        List<Reserva> reservasAMostrar = new LinkedList<>();
            for (Reserva x : reservas){
                if (x.getActiva() == true){
                    reservasAMostrar.add(x);
                }
            }
        return reservasAMostrar;
    }

}

