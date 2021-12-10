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

    <form:form action="generar-cupon" method="POST" modelAttribute="cerveceria" class="formulario-cupon">
        <label class="mr-sm-2" for="validationTooltip02">Seleccione una cerveceria</label>
        <form:select
                path="nombre"
                class="custom-select mr-sm-2"
                id="validationTooltip02"
                required="true"
                style="color:black !important;"
        >
            <option selected disabled value="" style="color:black !important;">Elegir...</option>

            <c:forEach var="i" items="${cervecerias}">
                <option value="${i.nombre}" style="color:black !important;">${i.nombre}</option>
            </c:forEach>
        </form:select>
        <button class="btn btn-primary " Type="Submit"/>Cupon de Descuento</button>
    </form:form>

    <c:if test="${not empty cupon}">

        <div class="cupon-descuento">
            <h2>Cerveceria: ${nameCerve}</h2>
            <p>Cupon de 10% de Descuento - Exclusivo Usuarios OnlyBeer</p>
            <p>Codigo: ${codigo}</p>
        </div>

    </c:if>

    <c:if test="${empty logeado}">
        <div style="display: flex; flex-direction: column">
            <div class="btn btn-danger" style="margin-bottom: 20px">Debes iniciar sesion.</div>
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

</body>
</html>


<style>


    @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
    * {
        font-family: "Poppins", sans-serif;
    }

    .cupon-descuento{
        background-color: white;
        box-shadow: 2px 2px 5px black;
        color: black;
        margin-top: 30px;
        padding: 30px;
        min-height: 200px;
    }

    .formulario-cupon{
        display: flex;
        flex-direction: column;
        justify-content: space-evenly;
        align-content: center;
        border: 1px solid darkslategrey;
        border-radius: 15px;
        color: white;
        padding: 30px;
        min-height: 200px;
        width: 60%;
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
