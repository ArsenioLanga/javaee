/**
 * VALIDADACAO DO FORMULARIO
 * @author Arsenio Langa 
 */
 
  function validar(){
	let nome = frmContacto.nome.value
	let telefone = frmContacto.telefone.value
		if(nome === ""){
			alert('Preencha o campo nome')
			frmContacto.nome.focus()
			return false
		}else if(telefone === ""){
			alert('Preencha o campo telefone')
			frmContacto.telefone.focus()
			return false
		}else{
			document.forms["formContacto"].submit()
		}
		
		
				
}
 
 