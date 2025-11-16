<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BodegApp - Login</title>

    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }

        html, body {
            height: 100%;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }

        .page {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* HEADER */
        header {
            background-color: #2c3e50;
            padding: 20px 30px;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }

        header img {
            height: 60px;
            margin-right: 15px;
        }

        header h1 {
            font-size: 38px;
            margin: 0;
        }

        /* FORMULARIO LOGIN */
        .login-container {
            max-width: 420px;
            margin: 60px auto;
            background: white;
            padding: 30px 35px;
            border-radius: 12px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.15);
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #2c3e50;
            font-size: 28px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            color: #34495e;
            font-weight: 600;
            margin-bottom: 6px;
        }

        .form-group input {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #bbb;
            border-radius: 8px;
            font-size: 16px;
        }

        .btn-login {
            width: 100%;
            padding: 12px;
            font-size: 17px;
            background-color: #1abc9c;
            border: none;
            color: white;
            font-weight: 700;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn-login:hover {
            background-color: #16a085;
        }

        /* FOOTER */
        footer {
            background-color: #2c3e50;
            color: white;
            text-align: center;
            padding: 14px;
            margin-top: auto;
        }

        .volver {
            text-align: center;
            margin-top: 15px;
        }

        .volver a {
            text-decoration: none;
            color: #16a085;
            font-weight: bold;
        }

        .volver a:hover {
            color: #13846e;
        }
    </style>
</head>
<body>

<div class="page">

    <!-- HEADER -->
    <header>
        <img src="${pageContext.request.contextPath}/assest/img/logo.png" alt="Logo BodegApp">
        <h1>BodegApp</h1>
    </header>

    <!-- FORMULARIO -->
    <div class="login-container">

        <h2>Iniciar Sesión</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="form-group">
                <label for="usuario">Usuarios:</label>
                <input type="text" id="correo" name="correo" required>
            </div>

            <div class="form-group">
                <label for="clave">Contraseña:</label>
                <input type="password" id="contrasena_hash" name="contrasena_hash" required>
            </div>

            <button type="submit" class="btn-login">Ingresar</button>
        </form>

        <div class="volver">
            <a href="${pageContext.request.contextPath}/index.jsp">← Volver al inicio</a>
        </div>
    </div>

    <!-- FOOTER -->
    <footer>
        &copy; 2025 BodegApp - Proyecto académico para la clase de Interfaz de Usuario
    </footer>

</div>

</body>
</html>
