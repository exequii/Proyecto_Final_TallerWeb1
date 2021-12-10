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
	<div class="fondo">
		<div class="login">
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="validar-login" method="POST" modelAttribute="datosLogin">
			    	<h3 class="form-signin-heading">Only Beer</h3>
					<hr class="colorgraph"><br>

					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<form:input path="email" id="email" type="email" class="form-control" style="margin-bottom:10px" placeholder="Ingrese su mail"/>
					<form:input path="password" type="password" id="password" class="form-control" style="margin-bottom:10px" placeholder="Ingrese su clave"/>
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
				</form:form>

				<%--Bloque que es visible si el elemento error no esta vacio	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>

				<c:if test="${not empty msg}">
					<h4><span>${msg}</span></h4>
					<br>
				</c:if>
			</div>
		</div>
	</div>

	<footer>
		<h3>Universidad Nacional de la Matanza</h3>
	</footer>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>

<style>

	@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
	* {
		font-family: "Poppins", sans-serif;
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

	.fondo{
		height: 600px;
		background: url("https://s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/07/27171104/cerveceria-1516.jpg") center no-repeat;
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

	span{
		color:white;
	}

</style>
