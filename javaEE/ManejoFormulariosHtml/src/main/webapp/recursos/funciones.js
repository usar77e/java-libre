function validarForma(forma){
    var usuario = forma.usuario;
    if(usuario.value == "" || usuario.value == "Escribir Usuario"){
        alert("Proporcione un nombre de usuario");
        usuario.Focus();
        usuario.select();
        return false;
    }
    
    var password = forma.password;
    if(password.value == "" || password.value.length < 3){
        alert("Proporcione un Password de al menos 3 caracteres");
        password.Focus();
        password.select();
        return false;
    }
    
    var tecnologias = forma.tecnologia;
    var checkSeleccionado = false;
    for(var i=0; i < tecnologias.length; i++){
        if(tecnologias[i].checked){
            checkSeleccionado = true;
        }
    }
    
    if(!checkSeleccionado){
        alert("debe seleccionar una tecnologia");
        return false;
    }
    
    var genero = forma.genero;
    var radioSeleccionado = false;
    for(var i=0; i < genero.length; i++){
        if(genero[i].checked){
            radioSeleccionado = true;
        }
    }
    
    if(!radioSeleccionado){
        alert("Debe seleccionar un genero");
        return false;
    }
    
    var ocupacion = forma.ocupacion;
    if(ocupacion.value == "" || ocupacion.value == "Seleccionar"){
        alert("Seleccione una ocupacion");
        return false;
    }
    
    //formulario es valido
    alert("Formulario valido");
    return true;
}

