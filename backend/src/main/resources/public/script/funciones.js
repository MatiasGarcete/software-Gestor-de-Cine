const listaPelicula = document.getElementById("nombrePelicula")
const selectPelicula = document.getElementById("pelicula")
mostrarFunciones();
select()
async function select(){
    const peli = await fetch(`http://localhost:8080/api/v1/pelicula`).then(response => response.json());
    peli.forEach(element => {
        listaPelicula.innerHTML += `
            <option value=${element.idPelicula}>${element.nombrePelicula}</option>
        `
        selectPelicula.innerHTML += `
            <option value=${element.idPelicula}>${element.nombrePelicula}</option>
        `
    });

}

async function llenarDatos(id){
    const data = await fetch(`http://localhost:8080/api/v1/funcion/${id}`).then(res => res.json()).then(res => res.objeto);
    const pelicula = await fetch(`http://localhost:8080/api/v1/pelicula`).then(res => res.json());
    
    pelicula.forEach(element => {
        if(data.idPelicula ==element.idPelicula){ 
            listaPelicula.value = (element.idPelicula).toString();
        }
    });
    console.log(pelicula);
    
    document.getElementById("idFuncion").value = data.idfuncion;
    document.getElementById("capacidad").value = data.capacidad;
    document.getElementById("fecha").value = data.fecha;
    document.getElementById("hora").value = data.hora;
    document.getElementById("precio").value = data.precio;
}

async function eliminarFuncion(id){
    try {
        const response = await fetch(`http://localhost:8080/api/v1/funcion/${id}`, {
            method: 'Delete', // Usar POST para login
            headers: {
                'Content-Type': 'application/json' // Asegúrate de que el cuerpo sea JSON
            }
        });

        if (response.ok) { // Verifica que la respuesta sea exitosa (200-299)
            location.reload(true);
            console.log('Eliminacion exitoso', data);
        } else {
            console.error('Error en la solicitud:', response.status, response.statusText);
        }

    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
}

createFunForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página

    let data = JSON.stringify({
        idfuncion: document.getElementById("idFun").value,
        capacidad: document.getElementById("cantidad").value,
        fecha: document.getElementById("dia").value,
        hora: document.getElementById("horario").value+":00",
        idPelicula: document.getElementById("pelicula").value,
        precio: document.getElementById("total").value,

    })

    try {
        const response = await fetch(`http://localhost:8080/api/v1/funcion`, {
            method: 'POST', // Usar POST para login
            headers: {
                'Content-Type': 'application/json' // Asegúrate de que el cuerpo sea JSON
            },
            body: data
        });

        console.log(response)
        
        if (response.ok) { // Verifica que la respuesta sea exitosa (200-299)
            location.reload(true);
            console.log('Login exitoso', data);
        } else {
            console.error('Error en la solicitud:', response.status, response.statusText);
        }

    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
})

funcionForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página

    let data = JSON.stringify({
        idfuncion: document.getElementById("idFuncion").value,
        capacidad: document.getElementById("capacidad").value,
        fecha: document.getElementById("fecha").value,
        hora: document.getElementById("hora").value,
        idPelicula: document.getElementById("nombrePelicula").value,
        precio: document.getElementById("precio").value,

    })

    console.log(data)

    try {
        const response = await fetch(`http://localhost:8080/api/v1/funcion/${document.getElementById("idFuncion").value}`, {
            method: 'PUT', // Usar POST para login
            headers: {
                'Content-Type': 'application/json' // Asegúrate de que el cuerpo sea JSON
            },
            body: data
        });

        console.log(response)
        
        if (response.ok) { // Verifica que la respuesta sea exitosa (200-299)
            location.reload(true);
            console.log('Login exitoso', data);
        } else {
            console.error('Error en la solicitud:', response.status, response.statusText);
        }

    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
});

async function obtenerNombrePelicula(id){
    const data = await fetch(`http://localhost:8080/api/v1/pelicula/${id}`).then(res => res.json());
    return data.objeto.nombrePelicula;
}

async function mostrarFunciones(){
    if(localStorage.getItem("usuario")){
        const tabla = document.getElementById("tabla");
        const data = await fetch("http://localhost:8080/api/v1/funcion").then(res => res.json());

        data.objeto.forEach(async funcion => {
            pelicula = await obtenerNombrePelicula(funcion.idPelicula)
            tabla.innerHTML += `
                <div id="tabla" class="container text-center">
                    <div class="row d-flex justify-content-center align-items-center bg-primary-subtle border border-primary-subtle rounded-3">
                        <div class="col">
                            <p class="m-3">${funcion.idfuncion}</p>
                        </div>
                        <div class="col">
                            <p class="m-3">${funcion.capacidad}</p> 
                        </div>
                        <div class="col">
                            <p class="m-3">${funcion.fecha}</p> 
                        </div>
                        <div class="col">
                            <p class="m-3">${funcion.hora}</p>
                        </div>
                        <div class="col">
                            <p class="m-3">${pelicula}</p>
                        </div>
                        <div class="col">
                            <p class="m-3">${funcion.precio}</p>
                        </div>
                        <div class="col">
                            <div class="d-flex justify-content-center align-items-center">
                                <button class="btn btn-outline-danger" onClick="eliminarFuncion(${funcion.idfuncion})"><i class="bi bi-trash-fill"></i></button>
                                <button class="btn btn-outline-secondary" onClick="llenarDatos(${funcion.idfuncion})" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="bi bi-pencil-square"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            `
        });
        
    }
}
