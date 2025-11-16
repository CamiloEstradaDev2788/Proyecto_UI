<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Usuario</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }
        h2 { color: #333; }
        form { background-color: #fff; padding: 20px; border-radius: 8px; max-width: 600px; margin: auto; box-shadow: 0 0 8px rgba(0,0,0,0.1); }
        input, select { width: 100%; padding: 8px; margin: 8px 0; border: 1px solid #ccc; border-radius: 4px; }
        button { background-color: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #0056b3; }
        a { display: block; text-align: center; margin-top: 10px; color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h2>Registrar Usuario</h2>

    <form action="${pageContext.request.contextPath}/usuarioController" method="post">
        <label>ID Empresa:</label>
        <input type="number" name="id_empresa" required>

        <label>ID Rol:</label>
        <input type="number" name="id_rol" required>

        <label>Primer Nombre:</label>
        <input type="text" name="nombre1" required>

        <label>Segundo Nombre:</label>
        <input type="text" name="nombre2">

        <label>Primer Apellido:</label>
        <input type="text" name="apellido1" required>

        <label>Segundo Apellido:</label>
        <input type="text" name="apellido2">

        <label>Cédula:</label>
        <input type="text" name="cedula" required>

        <label>Correo:</label>
        <input type="email" name="correo" required>

        <label>Contraseña:</label>
        <input type="password" name="contrasena_hash" required>

        <label>Fecha de Ingreso:</label>
        <input type="date" name="fecha_ingreso" required>

        <label>Salario:</label>
        <input type="number" step="0.01" name="salario" required>

        <label>Tipo de Contrato:</label>
        <input type="text" name="tipo_contrato" required>

        <label>Estado:</label>
        <select name="estado" required>
            <option value="true">Activo</option>
            <option value="false">Inactivo</option>
        </select>

        <button type="submit">Registrar</button>
    </form>

    <a href="UsuarioController">Volver al listado</a>
</body>
</html>
