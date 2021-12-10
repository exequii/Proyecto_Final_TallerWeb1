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
        <form:form action="crear-reserva" method="POST" modelAttribute="reserva" class="form-cerveceria">
            <h2>Realice su reserva!</h2>
            <div class="col-md-12 mb-3">
                <label for="cerveceria">Nombre Cerveceria:</label>
                <form:select
                        path="cerveceria"
                        class="form-control"
                        id="validationTooltip02"
                        required="true"
                >
                    <option selected disabled value="">Elegir...</option>

                    <c:forEach var="i" items="${cervecerias}">
                        <option value="${i.nombre}">${i.nombre}</option>
                    </c:forEach>
                    <%--                    <option value="Antares">Antares</option>--%>
                    <%--                    <option value="La Birra">La Birra</option>--%>
                    <%--                    <option value="Cervelar">Cervelar</option>--%>
                </form:select>
<%--                <form:input--%>
<%--                        path="cerveceria"--%>
<%--                        type="text"--%>
<%--                        class="form-control"--%>
<%--                        id="nombre"--%>
<%--                        name="nombre"--%>
<%--                        required="true"--%>
<%--                        placeholder="Ingrese el nombre del bar"--%>
<%--                ></form:input>--%>
            </div>

            <div class="col-md-12 mb-3">
                <label for="cantidad">Cantidad de personas:</label>
                <form:input
                        path="cantidadPersonas"
                        type="number"
                        class="form-control"
                        id="cantidad"
                        name="cantidad"
                        required="true"
                        placeholder="Ingrese la cantidad de comensales"
                ></form:input>
            </div>

            <div class="col-md-12 mb-3">
                <label for="horario">Horario de Reserva:</label>
                <form:input
                        path="horario"
                        type="number"
                        class="form-control"
                        id="horario"
                        name="horario"
                        required="true"
                        placeholder="Ingrese el horario"
                ></form:input>
            </div>


            <button class="btn btn-primary mb-3 ml-1" type="submit">
                Enviar
            </button>
        </form:form>
    </c:if>
    <c:if test="${empty logeado}">
        <div style="display: flex; flex-direction: column">
            <div class="btn btn-danger" style="margin-bottom: 20px">Debes iniciar sesion para poder ver reviews.</div>
            <a href="login" class="btn btn-danger">Ir a Login</a>
        </div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="btn btn-danger">${msg}</div>
    </c:if>
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

    .form-cerveceria {
        border: 2px solid rgb(71, 138, 226);
        border-radius: 20px;
        width: 80%;
        padding: 30px;
        background-color: rgb(1,1,1);
        color: white;
    }

    #reseña h5 {
        border-bottom: 1px solid rgb(197, 195, 195);
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
        background: url("https://images7.alphacoders.com/596/596513.jpg") center no-repeat;
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


</style>
