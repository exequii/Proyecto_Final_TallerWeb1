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
<div class="form-feedback">
    <h1 class="text-center m-1">Feedback de su visita</h1>

    <form:form action="dar-feedback" method="POST" modelAttribute="feedback">
        <div class="form-row">
            <div class="col-md-12 mb-3">
                <label class="mr-sm-2" for="validationTooltip02">Cerveceria</label>
                <form:select
                        path="cerveceria"
                        class="custom-select mr-sm-2"
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
            </div>

            <div id="puntuacion" class="col-md-12 mb-3">
                <label class="mr-sm-2" for="feedback">Puntuacion</label>
                <form:select path="puntuacion" class="custom-select mr-sm-2" id="feedback" required="true">
                    <option value="" selected disabled>Elegir...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </form:select>

            </div>

            <div class="col-md-12 mb-3">
                <label class="mr-sm-2" for="feedback">Cerveza a Recomendar</label>
                <form:select path="cerveza" class="custom-select mr-sm-2" id="feedback" required="true">
                    <option value="" selected disabled>Elegir...</option>
                    <option value="Honey">Honey</option>
                    <option value="Golden">Golden</option>
                    <option value="IPA">IPA</option>
                </form:select>
            </div>

            <div class="col-md-12 mb-3">
                <label for="exampleFormControlTextarea1">Comentarios:</label>
                <form:textarea
                        class="form-control"
                        id="exampleFormControlTextarea1"
                        rows="3"
                        path="comentario"
                        required="true"
                ></form:textarea>
            </div>

            <button class="btn btn-primary mb-3 ml-1" type="submit">
                Enviar
            </button>
        </div>
    </form:form>

    <c:if test="${not empty msg}">
        <h4><span>${msg}</span></h4>
        <br>
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
<!--
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        "use strict";
        window.addEventListener(
            "load",
            function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName("needs-validation");
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener(
                        "submit",
                        function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add("was-validated");
                        },
                        false
                    );
                });
            },
            false
        );
    })();
</script>
-->