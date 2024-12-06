const div = document.getElementById("informacion");
agregarContenido()
let precio = null;
let funcion = null;
async function agregarContenido(){
    const id = localStorage.getItem("verFuncion");
    console.log(id)
    if (id){
        const data = await fetch(`http://localhost:8080/api/v1/pelicula/${id}`).then(res => res.json());
        div.innerHTML = `
            <div class="m-3 d-flex justify-content-between align-items-center flex-column">
                <h3>${data.objeto.nombrePelicula}</h3>
                <div>
                    <h4>Funciones</h4>
                    <div id="tablaFunciones"></div>
                </div>
            </div>
            <div class="m-3">
                <img class="rounded-3" src="${data.objeto.portada}" alt="Portada de ${data.objeto.nombrePelicula}" width="400"/>
            </div>
        `

        llenarTabla(data.objeto.funcion)
    }
    
}

function llenarTabla(data){
    const tabla = document.getElementById("tablaFunciones");
    tabla.innerHTML += `
        <div class="row d-flex justify-content-center align-items-center bg-primary-subtle border border-primary-subtle rounded-3">
            <div class="col">
                <p class="m-3">Fecha</p>
            </div>
            <div class="col">
                <p class="m-3">Hora</p> 
            </div>
            <div class="col">
                <p class="m-3">Disponible</p> 
            </div>
            <div class="col">
                <p class="m-3">Precio</p> 
            </div>
            <div class="col"></div>
        </div>
    `
    console.log(data)
    data.forEach(fun => {
        tabla.innerHTML+=`
            <div class="row d-flex justify-content-center align-items-center border border-primary-subtle rounded-3">
                <div class="col">
                    <p class="m-3">${fun.fecha}</p>
                </div>
                <div class="col">
                    <p class="m-3">${fun.hora}</p> 
                </div>
                <div class="col">
                    <p class="m-3">${fun.capacidad}</p> 
                </div>
                <div class="col">
                    <p class="m-3">${fun.precio}</p> 
                </div>
                <div class="col">
                    <button class="btn btn-success" id="botonComprar" onclick="comprar(${fun.precio}, ${fun.idfuncion})" data-bs-toggle="modal" data-bs-target="#exampleModal">Comprar</button>
                </div>
            </div>
            <div class="d-flex justify-content-end">
                <div id="mensaje" style="display: none; background: #efa4a4;padding: 4px;border-radius: 5px;margin: 8px 0px;color: red; font-size: 12px; text-align: right"></div>
            </div>
        `
    });

    if(!localStorage.getItem("usuario")){
        document.getElementById("botonComprar").disabled = true;
        const mensaje = document.getElementById("mensaje");
        mensaje.style.display = "inline-block"
        mensaje.innerHTML = "<p class='m-0'>Ingrese a su cuenta</p>";
    };
}

function valor(accion){
    const cantidad = document.getElementById("cantidad")
    const total = document.getElementById("total")
    let valor = parseFloat(cantidad.value)

    if(accion == "+"){
        cantidad.value = valor + 1;
        total.value = precio * cantidad.value
    }else{
        if(valor > 0){
            cantidad.value = valor - 1;
            total.value = parseFloat(total.value) - precio;
        }
    }
}

async function finalizar(){
    let hoy = new Date()
    const anio = hoy.getFullYear(); // Obtiene el año completo (ejemplo: 2024)
    const mes = String(hoy.getMonth() + 1).padStart(2, '0'); // Obtiene el mes (0-indexado, +1) y lo convierte en formato 2 dígitos
    const dia = String(hoy.getDate()).padStart(2, '0'); // Obtiene el día y lo convierte en formato 2 dígitos
    const cantidad = parseFloat(document.getElementById("cantidad").value)

    if(cantidad > 0 ){
        let data = JSON.stringify({
            fechaReserva: `${anio}-${mes}-${dia}`,
            cantidadEntradas: parseInt(document.getElementById("cantidad").value),
            idFuncion: funcion,
            idUsuario: parseInt(JSON.parse(localStorage.getItem('usuario')).idUsuario)
        })
        console.log(data);

        try {
            const response = await fetch('http://localhost:8080/api/v1/reserva', {
                method: 'POST', // Usar POST para login
                headers: {
                    'Content-Type': 'application/json' // Asegúrate de que el cuerpo sea JSON
                },
                body: data
            });
            
            if (response.ok) { // Verifica que la respuesta sea exitosa (200-299)
                const data = await response.json();
                const updateUser = await fetch(`http://localhost:8080/api/v1/usuario/${data.objeto.idUsuario}`).then(res => res.json())
                localStorage.setItem("usuario", JSON.stringify(updateUser.objeto));
                window.location.href = "/entradas";
                console.log('Login exitoso', data);
            } else {
                console.error('Error en la solicitud:', response.status, response.statusText);
            }
    
        } catch (error) {
            console.error('Error en la solicitud:', error);
        }
    }
    else{
        alert("La cantidad de entradas es cero")
    }   
}

function comprar(p, f){
    precio = p; 
    funcion = f;
}