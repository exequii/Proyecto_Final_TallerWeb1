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
<a href="reserva-formulario">Reservas</a>
<c:if test="${not empty logeado}">
    <a href="mis-datos">Mis datos</a>
    <a href="cupon">Cupones</a>
    <a href="logout" class="btn btn-danger" style="padding: 15px 5px !important">Cerrar Sesion</a>
</c:if>
</header>
<div class="fondo3">
    <div class="form-feedback">
    <form:form action="dar-barrio" method="POST" modelAttribute="datos">
    <h3 class="form-signin-heading">Informacion Personal</h3>
    <hr class="colorgraph"><br>
    <p>Barrio:</p>
    <form:input path="barrio" class="form-control" placeholder="Escriba su barrio de residencia" style="margin-bottom:10px"/>
    <p>Direccion:</p>
    <form:input path="direccion" class="form-control" placeholder="Escriba su direccion de residencia" style="margin-bottom:10px"/>
    <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Actualizar Perfil</button>
    </form:form>
        <c:if test="${not empty msg}">
                <div class="btn btn-danger" style="margin-top: 20px">${msg}</div>
        </c:if>
    </div>

</div>

<footer>
    <h3>Universidad Nacional de la Matanza</h3>
</footer>

</body>
</html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
    * {
        font-family: "Poppins", sans-serif;
    }
    .form-feedback {
        border: 2px solid rgb(71, 138, 226);
        border-radius: 20px;
        width: 80%;
        padding: 30px;
        background-color: rgb(1,1,1,0.5);
        color: white;
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

    .fondo3{
        height: 600px;
        background: url("https://images7.alphacoders.com/596/596513.jpg") center no-repeat;
        display: flex;
        justify-content: center;
        align-content: center;
        align-items: center;
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

    .error{
        margin-top: 30px;
        padding: 10px 30px;
        border: 2px solid black;
        border-radius: 5px;
        background-color: darkred;
        color:white;
    }


</style>
