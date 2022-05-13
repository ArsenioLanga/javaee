<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<title>Agenda de Contactos</title>
<link rel="icon" href="imagens/favicon.phg">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Actualizar Contactos</h1>
	<form name="frmContacto" action="update">
		<table>

			<tr>
				<td><input type="text" name="id" id="input_id" value="<%out.print(request.getAttribute("id")); %>" readonly></td>
			</tr>
			
			<tr>
				<td><input type="text" name="nome" value="<%out.print(request.getAttribute("nome")); %>"></td>
			</tr>
			
			<tr>
				<td><input type="number" name="telefone" value="<%out.print(request.getAttribute("telefone")); %>"></td>
			</tr>

			<tr>
				<td><input type="email" name="email" value="<%out.print(request.getAttribute("email")); %>"></td>
			</tr>

		</table>
		<input type="submit" value="Actualizar" class="botao1"
			onclick="validar()">
	</form>
	<script type="text/javascript" src="js/javaScript.js"></script>
</body>
</html>