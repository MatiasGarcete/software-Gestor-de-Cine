const seccion = document.getElementById("gestionPelicula");

const listaGenero = document.getElementById("idGenero");
const listaCategoria = document.getElementById("idCalificacion");

const selectGenero = document.getElementById("genero");
const selectCategoria = document.getElementById("calificacion");

const peliculaForm = document.getElementById("peliculaForm");
const createForm = document.getElementById("createForm");
mostrarPeliculas()
select()

async function select(){
    const generos = await fetch(`http://localhost:8080/api/v1/genero`).then(response => response.json());
    generos.forEach(element => {
        listaGenero.innerHTML += `
            <option value=${element.idgenero}>${element.genero}</option>
        `
        selectGenero.innerHTML += `
            <option value=${element.idgenero}>${element.genero}</option>
        `
    });
    const categoria = await fetch(`http://localhost:8080/api/v1/calificacion`).then(response => response.json());
    categoria.forEach(element => {
        listaCategoria.innerHTML += `
            <option value=${element.idcalificacion}>${element.calificacion}</option>
        `
        selectCategoria.innerHTML += `
            <option value=${element.idcalificacion}>${element.calificacion}</option>
        `
    });
}

async function llenarDatos(id){
    const data = await fetch(`http://localhost:8080/api/v1/pelicula/${id}`).then(res => res.json()).then(res => res.objeto);
    const categoria = await fetch(`http://localhost:8080/api/v1/calificacion`).then(res => res.json());
    const generos = await fetch(`http://localhost:8080/api/v1/genero`).then(response => response.json());
    
    generos.forEach(element => {
        if(data.idGenero==element.idgenero){ 
            listaGenero.value = (element.idgenero).toString();
        }
    });
    console.log(categoria);
    console.log(generos);
    
    categoria.forEach(element => {
        if(data.idCalificacion==element.idcalificacion){ 
            console.log(element);
            listaCategoria.value = (element.idcalificacion).toString();
        }
    });
    
    document.getElementById("nombrePelicula").value = data.nombrePelicula;
    document.getElementById("tituloOriginal").value = data.tituloOriginal
    document.getElementById("descripcion").value = data.descripcion
    document.getElementById("portada").value = data.portada
    document.getElementById("duracion").value = data.duracion
    document.getElementById("anioEstreno").value = data.anioEstreno
    document.getElementById("idPelicula").value = data.idPelicula
}

async function eliminarPelicula(id){
    try {
        const response = await fetch(`http://localhost:8080/api/v1/pelicula/${id}`, {
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

createForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página
    let data = JSON.stringify({
        nombrePelicula: document.getElementById("nombre").value,
        tituloOriginal: document.getElementById("titulo").value,
        duracion: document.getElementById("tiempo").value,
        descripcion: document.getElementById("resumen").value,
        anioEstreno: document.getElementById("lanzamiento").value,
        portada: document.getElementById("imagen").value,
        idGenero: document.getElementById("genero").value,
        idCalificacion: document.getElementById("calificacion").value
    })

    try {
        const response = await fetch(`http://localhost:8080/api/v1/pelicula`, {
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
});

peliculaForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página

    let data = JSON.stringify({
        idPelicula: document.getElementById("idPelicula").value ,
        nombrePelicula: document.getElementById("nombrePelicula").value,
        tituloOriginal: document.getElementById("tituloOriginal").value,
        duracion: document.getElementById("duracion").value,
        descripcion: document.getElementById("descripcion").value,
        anioEstreno: document.getElementById("anioEstreno").value,
        portada: document.getElementById("portada").value,
        idGenero: document.getElementById("idGenero").value,
        idCalificacion: document.getElementById("idCalificacion").value
    })

    console.log(data)

    try {
        const response = await fetch(`http://localhost:8080/api/v1/pelicula/${document.getElementById("idPelicula").value}`, {
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

async function mostrarPeliculas(){
    if(localStorage.getItem("usuario")){
        const tabla = document.getElementById("tabla");
        const data = await fetch("http://localhost:8080/api/v1/pelicula").then(res => res.json());

        data.forEach(peli => {
            tabla.innerHTML += `
                <div id="tabla" class="container text-center">
                    <div class="row d-flex justify-content-center align-items-center bg-primary-subtle border border-primary-subtle rounded-3">
                        <div class="col">
                            <p class="m-3">${peli.idPelicula}</p>
                        </div>
                        <div class="col">
                            <p class="m-3">${peli.nombrePelicula}</p> 
                        </div>
                        <div class="col">
                            <p class="m-3">${peli.tituloOriginal}</p> 
                        </div>
                        <div class="col">
                            <p class="m-3">${peli.duracion}</p>
                        </div>
                        <div class="col" style="height: 180px; overflow: auto;">
                            <p class="m-3" style="height:120px">${peli.descripcion}</p>
                        </div>
                        <div class="col">
                            <p class="m-3">${peli.anioEstreno}</p> 
                        </div>
                        <div class="col" style="height: 180px; overflow: auto;">
                            <p class="m-3">${peli.portada}</p>
                        </div>
                        <div class="col">
                            <div class="d-flex justify-content-center align-items-center">
                                <button class="btn btn-outline-danger" onClick="eliminarPelicula(${peli.idPelicula})"><i class="bi bi-trash-fill"></i></button>
                                <button class="btn btn-outline-secondary" onClick="llenarDatos(${peli.idPelicula})" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="bi bi-pencil-square"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            `
        });
        
    }
}
