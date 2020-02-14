/* 
 * 
 * 
 * 
 * Biblioteca de Funciones JavaScript para los proyectos
 * de la Empresa Iterart®
 * 
 * Miercoles, 23 de sept de 2015.
 */


//Función que limita la cantidad de palabras en un input html.
//Autor: ander
function cantidadPalabras(obj, e, max) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return;
    maxPalabras = max;
    txt = obj.value.split(' ');
    if (txt.length > maxPalabras) {
        alert('Ya se ha ingresado el máximo permitido de palabras para este campo.');
        return false;
    }

}

function foco(idElemento) {
    document.getElementById(idElemento).focus();
}

//Funciones que limitan los tipos de datos de entrada
//Autor @ander - 25/09/2015 - Modificado 07/10/15

function soloNumeros(obj, e)
{
    //Solo retornar los números...
    var keynum = obj.event ? obj.event.keyCode : e.which;
    if ((keynum == 8) || (keynum == 46))
        return true;

    return /\d/.test(String.fromCharCode(keynum));
}

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for (var i in especiales) {
        if (key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) == -1 && !tecla_especial) {
        return false;
    }
}

//Escrito por @laloandroid
//Modificado por @ander
function sumarUnne() {
    f1 = $('#frmFinanciacion\\:aporteUnne').val();
    f2 = $('#frmFinanciacion\\:aporteOrganizavion').val();
    ft = parseFloat(f1);
    if (f1 > 5500) {
        alert("El aporte máximo de la UNNE es de $5500");
        $('#frmFinanciacion\\:costoTotal').val("0");
        $('#frmFinanciacion\\:aporteUnne').val("0");
        $('#frmFinanciacion\\:aporteOrganizavion').val("0");
    } else {
        $('#frmFinanciacion\\:costoTotal').val(parseFloat(f1) + parseFloat(f2));
        $('#frmFinanciacion\\:total').val(parseFloat(f1) + parseFloat(f2));
    }
}

//Añadido por @ander
//Mostrar password de input - 21/04/2016

function verOcultarPassword() {
    if ($('#campos\\:clave').get(0).type == 'password')
        $('#campos\\:clave').get(0).type = 'text';
    else
        $('#campos\\:clave').get(0).type = 'password';
}

function verOcultarPassword2() {
    if ($('#campos\\:clave2').get(0).type == 'password')
        $('#campos\\:clave2').get(0).type = 'text';
    else
        $('#campos\\:clave2').get(0).type = 'password';
}

//Tooltip
//Añadido por @ander
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});







