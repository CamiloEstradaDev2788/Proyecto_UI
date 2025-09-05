<%-- 
    Document   : contacto
    Created on : 5/09/2025, 3:07:52 p. m.
    Author     : Esteban
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>BodegApp - Contacto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: #2c3e50;
            color: white;
            padding: 15px;
            text-align: center;
        }
        main {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }
        .contact-container {
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            width: 400px;
        }
        .contact-container h2 {
            margin-bottom: 15px;
            color: #2c3e50;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #1abc9c;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #16a085;
        }
        .back-link {
            margin-top: 15px;
            display: block;
            text-align: center;
            text-decoration: none;
            color: #34495e;
        }
    </style>
</head>
<body>

<header>
    <h1>Contacto - BodegApp</h1>
</header>

<main>
    <div class="contact-container">
        <h2>Envíanos un mensaje</h2>
        <form action="index.jsp" method="post">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" placeholder="Tu nombre" required>

            <label for="correo">Correo</label>
            <input type="email" id="correo" name="correo" placeholder="Tu correo electrónico" required>

            <label for="mensaje">Mensaje</label>
            <textarea id="mensaje" name="mensaje" rows="4" placeholder="Escribe tu mensaje aquí..." required></textarea>

            <button type="submit">Enviar</button>
        </form>
        <a href="index.jsp" class="back-link">← Volver al inicio</a>
    </div>
</main>

</body>
</html>
