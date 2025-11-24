<%@page import="com.bodegapp.inventario.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>
<%@ page import="com.bodegapp.dashboard.dao.AlertaDTO" %>
<%@ page import="com.bodegapp.clientes.controller.ClientesController" %>
<%@ page import="java.util.List" %>


<%
    HttpSession sesion = request.getSession(false);

    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    int id_emprea = usuario.getID_EMPRESA();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <link rel="stylesheet" href="/assest/styles/styles.css">
</head>
    
    
<body>
    
    <div class="container">
        <!------------------------------Inicio Aside------------------------------->
        <aside>
            <div class="top">
                <div class="logo">
                    <img src="/assest/img/logo.png" alt="logo">
                </div>
                <div class="close" id="close-btn">
                    <span class="material-symbols-sharp">close</span>
                </div>
            </div>
            <div class="sidebar">
                <a href="<%= request.getContextPath() %>/dashboard.jsp" class="active">
                    <span class="material-symbols-sharp">dashboard</span>
                    <h3>Dashboard</h3>
                </a>
                <a href="<%= request.getContextPath() %>/InventarioController">
                    <span class="material-symbols-sharp">inventory</span>
                    <h3>Inventario</h3>
                </a>
            <a href="<%= request.getContextPath() %>/PersonalController">
                <span class="material-symbols-sharp">person</span>
                <h3>Personal</h3>
            </a>
                <a href="<%= request.getContextPath() %>/ClientesController">
                <span class="material-symbols-sharp">groups</span>
                <h3>Clientes</h3>
            </a>
                <a href="<%= request.getContextPath() %>/DistritoController">
                <span class="material-symbols-sharp">location_city</span>
                <h3>Distritos</h3>
            </a>
            
                <a href="logout">
                    <span class="material-symbols-sharp">logout</span>
                    <h3>Logout</h3>
                </a>
            </div>
        </aside>
        <!------------------------------Fin Aside------------------------------->
        <main>
                <div class="date">
                    <input type="date">
                </div>
                <div class="insights">
                    <!------------------Inicio Ventas----------------------------->
                    <div class="sales">
                        <span class="material-symbols-sharp">point_of_sale</span>
                            <div class="middle">
                                <div class="left">
                                    <h3>Ventas Totales</h3>
                                    <h1>$<%= request.getAttribute("totalVentas")%></h1>
                                </div>
                            </div>
                    </div>
                    <!------------------Fin Ventas----------------------------->
                    <!----------------Inicio gastos----------------------------->
                    <div class="bills">
                        <span class="material-symbols-sharp">bar_chart</span>
                            <div class="middle">
                                <div class="left">
                                    <h3>Gastos Totales</h3>
                                    <h1>$<%= request.getAttribute("gastosTotales") %></h1>
                                </div>
                            </div>
                    </div>
                    <!------------------Fin gastos----------------------------->
                    <!------------------Inicio ingresos------------------------>
                    <div class="income">
                        <span class="material-symbols-sharp">attach_money</span>
                            <div class="middle">
                                <div class="left">
                                    <h3>Ingresos</h3>
                                    <h1>$<%= request.getAttribute("gananciasTotales") %></h1>
                                </div>
                            </div>
                    </div>
                    <!------------------Fin ingresos----------------------------->
                </div>
                <!---==================Fin insights==================--->
                <!------------------Inicio productos recientes------------------------>
                <div class="recent-products">
                    <h2>Productos receientes</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Codigo Producto</th>
                                <th>Nombre</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th>fecha</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
<%
    List<ProductoDTO> lista = (List<ProductoDTO>) request.getAttribute("productosRecientes");
    if (lista != null) {
        for (ProductoDTO p : lista) {
%>
    <tr>
        <td><%= p.getCodigoProducto() %></td>
        <td><%= p.getDescripcion() %></td>
        <td><%= p.getCantidad() %></td>
        <td>$<%= p.getPrecio() %></td>
        <td><%= p.getFechaEntrega() %></td>
    </tr>
<%
        }
    }
%>
</tbody>

                    </table>
                    <a href="/InventarioController">Mostrar todo</a>
                </div>
                <!------------------Fin productos recientes------------------------>
        </main>
        <!---==================Fin main==================--->
        <!--==================Inicio right==================--->
        <div class="right">
            <div class="top">
                <button id="menu-btn">
                    <span class="material-symbols-sharp">menu</span>
                </button>
                <div class="theme-toggler">
                    <span class="material-symbols-sharp active">light_mode</span>
                    <span class="material-symbols-sharp">dark_mode</span>
                </div>
                <div class="profile">
                    <div class="info">
                        <%
                            com.bodegapp.usuarios.model.UsuarioModel user = 
                            (com.bodegapp.usuarios.model.UsuarioModel) session.getAttribute("usuario");
                        %>
                        <p>Hola, <b><%= user.getNOMBRE1() %> <%= user.getAPELLIDO1() %></b></p>
                        <small class="text-muted"><%= user.getCARGO() %></small>
                    </div>
                </div>
            </div>
        <!--==================Fin top-right==================--->
      <!-- ================== ALERTAS ================== -->
<!-- ================== ALERTAS ================== -->
<div class="important-dates">
    <h2>Alertas</h2>

    <div class="important">

        <%
            List<com.bodegapp.dashboard.dao.AlertaDTO> alertas =
                (List<com.bodegapp.dashboard.dao.AlertaDTO>) request.getAttribute("alertas");
        %>

        <% if (alertas == null || alertas.isEmpty()) { %>

            <p>No hay alertas por el momento.</p>

        <% } else { 
               for (com.bodegapp.dashboard.dao.AlertaDTO alerta : alertas) { %>

            <div class="date">
                <div class="image-element">
                    <span class="material-symbols-sharp">warning</span>
                </div>

                <div class="name-element">
                    <p>
                        <!-- TÍTULO -->
                        <b><%= alerta.getTitulo() %></b><br>

                        <!-- DESCRIPCIÓN -->
                        <%= alerta.getDescripcion() %><br>

                        <!-- EXTRA INFO -->
                        <small>
                            <% if (alerta.getVendedor() != null && !alerta.getVendedor().isEmpty()) { %>
                                <b>Vendedor:</b> <%= alerta.getVendedor() %><br>
                            <% } %>

                            <b>Solicitó:</b> <%= alerta.getSolicitud() %>
                        </small><br>

                        <!-- FECHA -->
                        <small><%= alerta.getFecha() %></small>
                    </p>
                </div>
            </div>

        <%   } 
           } 
        %>

    </div>
</div>
<!-- ================== FIN ALERTAS ================== -->


<!-- ================== Fin Right ================== -->

</body>
</html>