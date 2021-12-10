<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
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

		<c:if test="${not empty logeado}">
			<a href="reserva-formulario">Reservas</a>
			<a href="mis-datos">Mis datos</a>
			<a href="cupon">Cupones</a>
			<a href="logout" class="btn btn-danger" style="padding: 15px 5px !important">Cerrar Sesion</a>
		</c:if>
	</header>
	<div class="fondo4">
		<c:if test="${not empty logeado}">
		<div class = "container home">
			<h1 style="text-align: center">Bienvenidos a Only Beer</h1>
			<a href="grafico" class="btn btn-default center" style="margin-bottom: 15px">GRAFICOS</a>
			<div class="center" style="display: flex;">
			<form:form action="buscar-cerveceria" method="POST" modelAttribute="buscador" style="width:100%;" class="center">

			<form:input path="nombre" type="text" placeholder="Buscar" style="width: 80%; padding: 10px;"/>
				<form:select path="tipo" required="true" style="padding: 10px;">
					<option disabled selected>Elegir..</option>
					<option value="barrio">Barrio</option>
					<option value="nombre">Nombre</option>
					<option value="radio">Radio</option>
				</form:select>
				<button class="btn btn-primary mb-3 ml-1" type="submit" style="padding: 10px;">
					Buscar
				</button>
			</form:form>
			</div>
			<div id="botones">
				<a href="feedback" class="btn btn-info" style="margin-right: 30px">Cuentanos tu Experiencia</a>
				<a href="review" class="btn btn-danger">Reviews de Usuarios</a>
				<a href="cerveceria" class="btn btn-success">Adhiere tu cerveceria</a>
			</div>
			<c:if test="${infoUsuario == 0}">
				<a href="infoUsuario" style="color:white; text-align: center; margin-top: 20px">Complete la informacion de su perfil.</a>
			</c:if>
			<c:if test="${infoUsuario != 0}">
				<p style="color: white; text-align: center; margin-top: 20px">Informacion de perfil completa.</p>
			</c:if>

			<c:if test="${not empty msg}">
				<h4><span>${msg}</span></h4>
				<br>
			</c:if>

			<c:if test="${not empty cerveceriaEncontrada}">
				<h3>Resultados de su busqueda:</h3>
				<div id="reseña" class="m-2 container">
						<div class="m-2 d-flex">
							<p class="me-3"><strong>- Cerveceria:</strong> ${cerveceriaEncontrada.nombre}</p>
							<p><strong>- Direccion:</strong> ${cerveceriaEncontrada.direccion}</p>
							<p><strong>- Barrio:</strong> ${cerveceriaEncontrada.barrio}</p>
							<p><strong>- Puntuacion Promedio: </strong>${cerveceriaEncontrada.promedioPuntuacion}</p>

						</div>
				</div>
				<div style="width: 100%; height: 500px; display: flex; justify-content: center">
					<img src="https://maps.googleapis.com/maps/api/staticmap?center=${cerveceriaEncontrada.barrio},Buenos Aires,Argentina&zoom=14&size=500x400&maptype=roadmap
					&markers=color:red%7Clabel:S%7C${cerveceriaEncontrada.latitud},${cerveceriaEncontrada.longitud}
					&key=ACAVALAKEYAPI" style="margin-top: 20px; width: 50%; height: 80%">
				</div>

			</c:if>

			<c:if test="${not empty listaEncontrada}">
				<h3>Resultados de su busqueda:</h3>
				<c:forEach items="${listaEncontrada}" var="cervecerias">
				<div id="reseña" class="m-2 container" style="margin-bottom: 15px">
					<div class="m-2 d-flex">
						<c:if test="${cervecerias.mejorPunteada == true}">
							<p class="mejorPunt">ESTA ES LA CERVECERIA MEJOR PUNTUADA</p>
						</c:if>
						<p class="me-3"><strong>- Cerveceria:</strong> ${cervecerias.nombre}</p>
						<p><strong>- Direccion:</strong> ${cervecerias.direccion}</p>
						<p><strong>- Barrio:</strong> ${cervecerias.barrio}</p>
						<p><strong>- Puntuacion Promedio: </strong>${cervecerias.promedioPuntuacion}</p>

					</div>
				</div>
				</c:forEach>
				<div style="width: 100%; height: 500px; display: flex; justify-content: center">
					<img src="https://maps.googleapis.com/maps/api/staticmap?center=${barrio},Buenos Aires,Argentina&zoom=12&size=500x400&maptype=roadmap
					<c:forEach items="${coordenadas}" var="posicion">
					&markers=color:red%7Clabel:${posicion.index}%7C${posicion.latitud},${posicion.longitud}
					</c:forEach>
					&key=ACAVALAKEYAPI" style="margin-top: 20px; width: 50%; height: 80%">
				</div>

			</c:if>

			<c:if test="${not empty cervecerias}">
				<h3>Resultados de su busqueda:</h3>
				<c:forEach items="${cervecerias}" var="x">
					<c:if test="${x.mostrar == true}">
					<div id="reseña" class="m-2 container" style="margin-bottom: 15px">
						<div class="m-2 d-flex">
							<c:if test="${x.mejorPunteada == true}">
								<p class="mejorPunt">ESTA ES LA CERVECERIA MEJOR PUNTUADA</p>
							</c:if>
							<p class="me-3"><strong>- Cerveceria:</strong> ${x.nombre}</p>
							<p><strong>- Direccion:</strong> ${x.direccion}</p>
							<p><strong>- Barrio:</strong> ${x.barrio}</p>
							<p><strong>- Puntuacion Promedio: </strong>${x.promedioPuntuacion}</p>
						</div>
					</div>
					</c:if>
				</c:forEach>
				<div style="width: 100%; height: 500px; display: flex; justify-content: center">
					<img src="https://maps.googleapis.com/maps/api/staticmap?center=${barrio},Buenos Aires,Argentina&zoom=11&size=500x400&maptype=roadmap
					<c:forEach items="${coordenadas}" var="posicion">
					&markers=color:red%7Clabel:${posicion.index}%7C${posicion.latitud},${posicion.longitud}
					</c:forEach>
					&key=ACAVALAKEYAPI" style="margin-top: 20px; width: 50%; height: 80%">
				</div>

			</c:if>

			<c:if test="${not empty distancia}">
				<p style="color:white;">La distancia entre los puntos es: ${distancia} km.</p>
			</c:if>

		</div>
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
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!--
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYQIPNiW4ir4_kCFbD7ekM1EDwARZ3vTI&callback=initMap" async defer></script>
		<script>
			var map;
			function initMap() {
				map = new google.maps.Map(document.getElementById("map"), {
					center: { lat: -34.397, lng: 150.644 },
					zoom: 10,
				});
			}
		</script>
-->

	</body>
</html>


<style>


	@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
	* {
		font-family: "Poppins", sans-serif;
	}

	.mejorPunt{
		color:gold;
		font-style: italic;
		font-weight: bold;
		border-bottom: 1px solid gold;
	}

	h1{
		color: white;
	}

	h3{
		color: white;
	}

	#reseña {
		border: 1px solid black;
		box-shadow: 2px 2px 10px black;
		width: 50%;
		height: 160px !important;
		background-color: white;
	}

	#reseña h5 {
		border-bottom: 1px solid rgb(197, 195, 195);
	}

	.center{
		display: flex;
		justify-content: center;
		align-content: center;
		align-items: center;
	}

	.home{
		min-height: 400px;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		border: 2px solid white;
		border-radius: 20px;
		background-color: rgb(1,1,1,0.5);
		margin-top: 140px;
		margin-bottom: 70px;
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
	}
	header a:hover{
		background-color: black;
		text-decoration: none;
		color: white;
	}

	#botones{
		margin-top: 20px;
		display: flex;
		justify-content: space-evenly;
	}
	.fondo4{
		min-height: 600px;
		background: url("https://azafranbolivia.com/wp-content/uploads/2020/08/cervezas-bolivianas-bolivia-artesanales.jpg") center repeat-y;
		display: flex;
		justify-content: center;
		align-items: center;
		align-content: center;
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

	span{
		color:white;
	}


</style>