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
    </c:if>
    <a href="ir-a-registrarme">Registro</a>
    <c:if test="${not empty logeado}">
        <a href="reserva-formulario">Reservas</a>
        <a href="mis-datos">Mis datos</a>
        <a href="cupon">Cupones</a>
        <a href="logout" class="btn btn-danger" style="padding: 15px 5px !important">Cerrar Sesion</a>
    </c:if>
</header>
<div class="fondo2">
<div class="login">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="registrarme" method="POST" modelAttribute="datos">
            <h3 class="form-signin-heading">Registro</h3>
            <hr class="colorgraph"><br>

            <form:input path="email" id="email" class="form-control" placeholder="Escriba un mail" style="margin-bottom:10px"/>
            <form:input path="clave" type="password" id="clave" class="form-control" placeholder="Ingrese un Password" style="margin-bottom:10px"/>
            <form:input path="repiteClave" type="password" id="clave" class="form-control" placeholder="Repita la Password" style="margin-bottom:10px"/>
            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Registrarme</button>
        </form:form>

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

    .fondo2{
        height: 600px;
        background: url("https://pizzbur.com/wp-content/uploads/2017/09/prevenir-enfermedades-cardiovasculares-y-neurodegenerativas-con-un-consumo-moderado-de-cerveza-1920.jpg") center no-repeat;
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