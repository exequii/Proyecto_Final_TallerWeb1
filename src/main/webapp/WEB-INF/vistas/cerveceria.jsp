<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
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
        <c:if test="${not empty admin}">
        <form:form action="crear-cerveceria" method="POST" modelAttribute="cerveceria" class="form-cerveceria">
            <h2>Adhiera su Cerveceria!</h2>
            <div class="col-md-12 mb-3">
                <label for="nombre">Nombre:</label>
                <form:input
                        path="nombre"
                        type="text"
                        class="form-control"
                        id="nombre"
                        name="direccion"
                        required="true"
                        placeholder="Ingrese el nombre del bar"
                ></form:input>
            </div>

            <div class="col-md-12 mb-3">
                <label for="direccion">Direccion:</label>
                <form:input
                        path="direccion"
                        type="text"
                        class="form-control"
                        id="direccion"
                        name="direccion"
                        required="true"
                        placeholder="Ingrese la direccion del bar"
                ></form:input>
            </div>

            <div class="col-md-12 mb-3">
                <label for="barrio">Barrio:</label>
                <form:input
                        path="barrio"
                        type="text"
                        class="form-control"
                        id="barrio"
                        name="bario"
                        required="true"
                        placeholder="Ingrese barrio del bar"
                ></form:input>
            </div>


            <button class="btn btn-primary mb-3 ml-1" type="submit">
                Enviar
            </button>
        </form:form>
        </c:if>
    </c:if>

    <c:if test="${empty admin}">
        <div style="display: flex; flex-direction: column">
            <div class="btn btn-danger" style="margin-bottom: 20px">Debes ser administrador para poder registrar cervecerias</div>
            <a href="home" class="btn btn-danger">Ir al Home</a>
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
    .form-cerveceria {
        border: 2px solid rgb(71, 138, 226);
        border-radius: 20px;
        width: 80%;
        padding: 30px;
        background-color: rgb(1,1,1);
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