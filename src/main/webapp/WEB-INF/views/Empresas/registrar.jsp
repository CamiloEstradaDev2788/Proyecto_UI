<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Empresa</title>
</head>
<body>
    <h2>Registrar Empresa</h2>
    <form action="${pageContext.request.contextPath}/empresaController" method="post">
        <input type="text" name="nit" placeholder="NIT" required><br>
        <input type="text" name="nombre" placeholder="Nombre" required><br>
        <input type="text" name="direccion" placeholder="Dirección"><br>
        <input type="text" name="telefono" placeholder="Teléfono"><br>
        <input type="text" name="logo" placeholder="URL del logo"><br>
        <button type="submit">Registrar</button>
    </form>
    <p style="color:red">${error}</p>
</body>
</html>
