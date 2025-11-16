<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BodegApp - Inicio</title>

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
            justify-content: center; /* centrado */
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

        /* Botón login a la derecha */
        .login-header {
            position: absolute;
            right: 30px;
            top: 50%;
            transform: translateY(-50%);
        }

        .login-header a {
            background-color: #1abc9c;
            padding: 10px 18px;
            color: white;
            font-weight: 600;
            text-decoration: none;
            border-radius: 8px;
        }

        .login-header a:hover {
            background-color: #16a085;
        }

        /* HERO */
        main {
            flex: 1;
            text-align: center;
            padding: 40px 20px;
        }

        main h2 {
            color: #2c3e50;
            font-size: 32px;
            margin-bottom: 15px;
        }

        main p {
            color: #555;
            font-size: 18px;
            max-width: 700px;
            margin: 0 auto 10px auto;
        }

        /* NAV */
        nav {
            background-color: #34495e;
            padding: 12px;
            text-align: center;
        }

        nav a {
            color: white;
            padding: 8px 14px;
            text-decoration: none;
            font-weight: 600;
            margin: 0 5px;
            border-radius: 6px;
        }

        nav a:hover {
            background-color: #1abc9c;
        }

        /* FOOTER PEGADO ABAJO */
        footer {
            background-color: #2c3e50;
            color: white;
            text-align: center;
            padding: 14px;
            margin-top: auto;
        }
    </style>
</head>
<body>

<div class="page">

    <!-- HEADER centrado -->
    <header>
        <img src="${pageContext.request.contextPath}/assest/img/logo.png" alt="Logo BodegApp">
        <h1>BodegApp</h1>

        <div class="login-header">
            <a href="${pageContext.request.contextPath}/login.jsp">Iniciar sesión</a>
        </div>
    </header>

    <!-- HERO SECTION -->
    <main>
        <h2>Bienvenido a BodegApp</h2>
        <p>
            Solución para pequeñas empresas que necesitan llevar un control eficiente de su inventario,
            ventas, proveedores y usuarios.
        </p>
        <p>Selecciona una opción del menú para continuar.</p>
    </main>

    <!-- NAV -->
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
        <a href="${pageContext.request.contextPath}/contacto.jsp">Contacto</a>
    </nav>

    <!-- FOOTER -->
    <footer>
        &copy; 2025 BodegApp - Proyecto académico
    </footer>

</div>

</body>
</html>
