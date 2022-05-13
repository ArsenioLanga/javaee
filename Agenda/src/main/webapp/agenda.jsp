<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contacto");
%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<title>Agenda de Contactos</title>
<link rel="icon" href="imagens/favicon.phg">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contactos</h1>
	<a href="novo.html" class="botao1">Novo</a>
	<a href="relatorio" class="botao2">Gerer Relatorio</a>
	<table id="listaContactos">
		<thead>
			<tr>
				<th>
					ID		
				</th>
				<th>
					NOME		
				</th>
				<th>
					TELEFONE		
				</th>
				<th>
					EMAIL		
				</th>
				<th>
					ACCOES		
				</th>
			</tr>	
		</thead>
		<tbody>
			<% for(int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td> <%= lista.get(i).getId() %> </td>
					<td> <%= lista.get(i).getNome() %> </td>
					<td> <%= lista.get(i).getTelefone() %> </td>
					<td> <%= lista.get(i).getEmail() %> </td>
					<td> <a href="select?id=<%=lista.get(i).getId()%>" class="botao2">EDITAR</a>  <a href="javascript: confirmar(<%=lista.get(i).getId()%>)"  class="botao1">ELIMINAR</a></td>
					
				</tr>
			<%} %>
			
		</tbody>
	</table>
	<script type="text/javascript" src="js/confirmador.js"></script>
</body>
</html>