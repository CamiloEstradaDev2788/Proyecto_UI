<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Rol</title>
</head>
<body>
    <h2>Registrar Rol</h2>
    <form action="${pageContext.request.contextPath}/rolController" method="post">
        <input type="text" name="id_empresa" placeholder="Id de la empresa" required><br>
        <input type="text" name="nombre_rol" placeholder="Nombre del Rol" required><br>
        <input type="text" name="descripcion" placeholder="Descripción"><br>
        <button type="submit">Registrar</button>
    </form>
    <p style="color:red">${error}</p>
</body>
</html>
