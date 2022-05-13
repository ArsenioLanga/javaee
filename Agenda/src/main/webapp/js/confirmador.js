/**
 * CONFIRMAR A EXCLUSAO DO CONTACTO
 * @param id
 */
 
 
 function confirmar(id){
	let resposta = confirm("Tem certeza que pretende eliminar?")
	if(resposta === true){
		window.location.href = "delete?id=" + id;
		}	
}
 