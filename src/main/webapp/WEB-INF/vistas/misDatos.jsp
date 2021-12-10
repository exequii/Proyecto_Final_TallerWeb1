<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
            crossorigin="anonymous"
    />
</head>
<body>
<header>
    <c:if test="${not empty logeado}">
        <a href="home">Home</a>
    </c:if>
    <c:if test="${empty logeado}">
        <a href="login">Home</a>
        <a href="ir-a-registrarme">Registro</a>
    </c:if>
    <a href="reserva-formulario">Reservas</a>
<c:if test="${not empty logeado}">
    <a href="mis-datos">Mis datos</a>
    <a href="cupon">Cupones</a>
    <a href="logout" class="btn btn-danger" style="padding: 15px 5px !important">Cerrar Sesion</a>
</c:if>


</header>
<div class="fondo3">
    <c:if test="${not empty logeado}">
    <h2 class="titulo" style="margin-bottom: 30px">Perfil de ${usuario}:</h2>
        <h3>Reviews del usuario:</h3>
    <!--Si recibe un mensaje de error significa que no encontro nada en la base-->
        <c:if test="${not empty msg}">
            <h4><span class="error">${msg}</span></h4><br>
        </c:if>
        <c:if test="${empty msg}">


            <c:if test="${notificaciones != 0}">
                <h4><span class="notificacion">Tienes ${notificaciones} nuevos feedbacks</span></h4><br>
            </c:if>


            <c:forEach items="${datosReview}" var="reviews">
        <div id="reseña" class="m-2 container">
                <form:form action="eliminar-review" method="POST" modelAttribute="datos">
                <div class="m-2 d-flex">
                    <form:input type="hidden" path="id" value="${reviews.id}"/>
                    <p class="me-3"><strong>- Cerveceria:</strong> ${reviews.cerveceria}</p>
                    <p><strong>- Cerveza Consumida:</strong> ${reviews.cerveza}</p>
                </div>
                <div class="m-2 d-flex">
                    <p class="me-2"><strong>- Puntuacion:</strong> ${reviews.puntuacion}</p>
                    <img
                            width="30px"
                            height="25px"
                            src="https://w7.pngwing.com/pngs/427/480/png-transparent-gold-star-star-star-angle-orange-symmetry-thumbnail.png"
                    />
                </div>
                <div class="m-2">
                    <p><strong>- Comentarios:</strong> ${reviews.comentario}</p>
                </div>
                <div class="m-2">
                 <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Eliminar</button>
                </div>

                </form:form>
        </div>
    </c:forEach>
    </c:if>

        <h3>Reservas del usuario:</h3>
        <c:if test="${not empty msg1}">
            <h4><span class="error">${msg1}</span></h4><br>
        </c:if>
        <c:if test="${empty msg2}">
        <c:forEach items="${misReservas}" var="reservaa">

            <div id="reseña" class="m-2 container">
                <form:form action="eliminar-reserva" method="POST" modelAttribute="reserva">
                    <form:input type="hidden" path="id" value="${reservaa.id}"/>
                    <form:input type="hidden" path="cerveceria" value="${reservaa.cerveceria.nombre}"/>
                <div class="m-2 d-flex">
                    <p class="me-3"><strong>- Cerveceria:</strong> ${reservaa.cerveceria.nombre}</p>
                    <p><strong>- Cantidad de Personas:</strong> ${reservaa.cantidadPersonas}</p>
                </div>
                <div class="m-2">
                    <p><strong>- Horario:</strong> ${reservaa.horario}</p>
                </div>
                <div class="m-2">
                    <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Eliminar</button>
                </div>
                </form:form>

            </div>
        </c:forEach>
        </c:if>

        <h3>Cupones del usuario:</h3>
        <c:if test="${not empty msg4}">
            <h4><span class="error">${msg4}</span></h4><br>
        </c:if>
        <c:if test="${empty msg4}">

            <c:forEach items="${cupones}" var="cupon">
                <div class="cupon-descuento m-2 container">
                    <div class="m-2 d-flex" style="display: flex; flex-direction: column">
                        <p>Tenes un cupon de descuento en cerveceria <strong>${cupon.cerveceriaDelCupon.nombre}</strong></p>
                        <p>Tu codigo es: ${cupon.codigo}</p>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${not empty admin}">
            <h3>Cervecerias del Administrador:</h3>
            <c:if test="${not empty msg2}">
                <h4><span class="error">${msg2}</span></h4><br>
            </c:if>
            <c:if test="${empty msg2}">
            <c:forEach items="${misCervecerias}" var="cervecerias">
                <div id="reseña" class="m-2 container">
                    <form:form action="eliminar-cerveceria" method="POST" modelAttribute="cerveceria">
                    <form:input type="hidden" path="id" value="${cervecerias.id}"/>
                    <div class="m-2 d-flex">
                        <p class="me-3"><strong>- Cerveceria:</strong> ${cervecerias.nombre}</p>
                        <p><strong>- Direccion:</strong> ${cervecerias.direccion}</p>
                    </div>
                    <div class="m-2">
                        <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Eliminar</button>
                    </div>
                    </form:form>
                </div>
            </c:forEach>
            </c:if>
        </c:if>

        <c:if test="${not empty barrio}">
        <h3>Cervecerias cerca de ${barrio}:</h3>

            <div id="map"></div>



        </c:if>

    </c:if>
    <c:if test="${empty logeado}">
        <div style="display: flex; flex-direction: column">
            <div class="btn btn-danger" style="margin-bottom: 20px">Debes iniciar sesion para poder ver reviews.</div>
            <a href="login" class="btn btn-danger">Ir a Login</a>
        </div>
    </c:if>
</div>

<footer>
    <h3>Universidad Nacional de la Matanza</h3>
</footer>

<script async
        src="https://maps.googleapis.com/maps/api/js?key=acavalakeyapi&callback=initMap&v=weekly"
></script>
<script>
    var latitudes = new Array();
    <c:forEach items="${latitudCerveceria}" var="latitud">
        latitudes.push(${latitud});
    </c:forEach>
        var longitudes = new Array();
    <c:forEach items="${longitudCerveceria}" var="longitud">
        longitudes.push(${longitud});
    </c:forEach>
</script>
<script>
    function initMap() {
        const myLatLng = { lat: ${usuarioLat}, lng: ${usuarioLong}};
        const map = new google.maps.Map(document.getElementById("map"), {
            zoom: 15,
            center: myLatLng,
        });
        new google.maps.Marker({
            position: myLatLng,
            map
        });

        for (var i = 0; i < ${cantidadCervecerias}; i++) {
            new google.maps.Marker({
                position: { lat: latitudes[i], lng: longitudes[i]},
                map
            });
            console.log(latitudes[i],longitudes[i])
        }
    }
</script>

<script>

    <%--var longitudCerveceria = [${longitudCerveceria}]--%>
    <%--console.log(latitudCerveceria,longitudCerveceria)--%>
</script>
</body>
</html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
    * {
        font-family: "Poppins", sans-serif;
    }

    .cupon-descuento{
        padding: 20px;
        background-color: white;
        box-shadow: 2px 2px 10px black;
        margin-top: 10px;
        margin-bottom: 10px;
        display: flex;
        flex-direction: column;
        align-content: center;
        justify-content: space-evenly;
        width: 50%;
    }

    .mapita{
        margin-bottom: 20px;
    }

    .titulo{
        border: 2px solid white;
        border-radius: 15px;
        padding: 5px 20px;
        color: black;
    }
    #reseña {
        border: 1px solid black;
        box-shadow: 2px 2px 10px black;
        width: 50%;
        height: 260px !important;
        background-color: white;
    }

    #reseña h5 {
        border-bottom: 1px solid rgb(197, 195, 195);
    }
    #map {
        width: 600px;
        height: 600px;
    }

    #logo {
        width: 150px;
        position: relative;
        top: -120px;
        left: 70%;
    }

    .logo {
        width: 150px;
        position: relative;
        top: -120px;
        left: 70%;
    }

    header{
        height: 50px;
        background-color: #3c3c3c;
        display: flex;
        justify-content: space-evenly;
    }

    header a{
        padding: 15px 5px;
        color:white;
        transition: 1s all ease-in-out;
        text-decoration: none;
    }
    header a:hover{
        background-color: black;
        text-decoration: none;
        color: white;
    }

    .fondo3{
        min-height: 600px;
        background: url("https://images7.alphacoders.com/596/596513.jpg") center repeat-y;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-content: center;
        align-items: center;
    }

    .login h3{
        color: white;
        text-align: center;
    }

    footer{
        display: flex;
        justify-content: center;
        align-content: center;
        height: 130px;
        background-color: #3c3c3c;
        color: white;
        text-align: center;
    }

    .error{
        margin-top: 50px;
        padding: 10px 30px;
        border: 2px solid white;
        border-radius: 5px;
        background-color: #de6a6a;
        color:white;
    }
    .notificacion{
        margin-top: 50px;
        padding: 10px 30px;
        border: 2px solid white;
        border-radius: 5px;
        background-color: #59C3C3;
        color:white;
    }


</style>
