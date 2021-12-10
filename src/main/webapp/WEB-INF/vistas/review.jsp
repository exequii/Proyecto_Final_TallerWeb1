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
    <h2>REVIEWS DE USUARIOS:</h2>

        <!-- Filtrar reviews -->
        <div id="puntuacion" class="form-puntuacion">
        <form action="reviews-filtradas">
            <div class="form-row col-md-12 mb-3">
                <label class="mr-sm-2" for="puntuacion">Filtrar por puntuacion</label>
                <select name="puntuacion" class="custom-select mr-sm-2" style="width: 75%; padding: 8px;">
                    <option value="" selected disabled>Elegir...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            <button class="btn btn-primary mb-3 ml-1" type="submit">Enviar</button>
            </div>
        </form>
        </div>


        <!--Si recibe un mensaje de error significa que no encontro nada en la base-->
        <c:if test="${not empty msg}">
            <h4><span class="error">${msg}</span></h4><br>
        </c:if>
        <c:if test="${empty msg}">

    <c:forEach items="${datosReview}" var="review">
        <div id="reseña" class="m-2 container">
            <h5 class="m-2">Usuario: ${review.usuario.email}</h5>
                <div class="m-2 d-flex">
                    <p class="me-3"><strong>- Cerveceria:</strong> ${review.cerveceria}</p>
                    <p><strong>- Cerveza Consumida:</strong> ${review.cerveza}</p>
                </div>
                <div class="m-2 d-flex">
                    <p class="me-2"><strong>- Puntuacion:</strong> ${review.puntuacion}</p>
                    <img
                            width="30px"
                            height="25px"
                            src="https://w7.pngwing.com/pngs/427/480/png-transparent-gold-star-star-star-angle-orange-symmetry-thumbnail.png"
                    />
                </div>
                <div class="m-2">
                    <p><strong>- Comentarios:</strong> ${review.comentario}</p>
                </div>
                <img
                        src="https://www.brewermap.com.ar/assets/img/items/156/club.png"
                        id="logo"
                />

        </div>
    </c:forEach>
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
</body>
</html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
    * {
        font-family: "Poppins", sans-serif;
    }
    #reseña {
        border: 1px solid black;
        box-shadow: 2px 2px 10px black;
        width: 50%;
        height: 230px !important;
        background-color: white;
    }

    #reseña h5 {
        border-bottom: 1px solid rgb(197, 195, 195);
    }

    #logo {
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

    .error{
        margin-top: 30px;
        padding: 10px 30px;
        border: 2px solid black;
        border-radius: 5px;
        background-color: darkred;
        color:white;
    }
    .form-puntuacion {
        border: 2px solid rgb(71, 138, 226);
        border-radius: 20px;
        width: 80%;
        padding: 30px;
        background-color: rgb(1,1,1,0.5);
        color: white;
    }


</style>
