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

<div class="fondo4">

    <h1>Grafico de cervecerias con su Promedio Puntuacion</h1>
    <img src="<c:url value='/img/estadisticas.jpeg' />" width="600" height="400"/>

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
        flex-direction: column;
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