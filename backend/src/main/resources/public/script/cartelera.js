async function infoPelicula(id){
    const data = await fetch(`http://localhost:8080/api/v1/pelicula/${id}`).then(res => res.json());
    const modelPelicula = document.getElementById("infoPelicula");

    modelPelicula.innerHTML = `
        <div class="d-grid">
            <div class="row d-flex justify-content-center align-items-center ">
                <div class="col">
                    <h2>${data.objeto.nombrePelicula}</h2>
                    <h4>Descripcion</h4>
                    <p>${data.objeto.descripcion}</p>
                    <p><b>Duracion:</b> ${data.objeto.duracion}</p>
                    <button onclick="verFunciones(${data.objeto.idPelicula})" class="btn btn-success">Ver funciones</button>
                </div>
                <div class="col d-flex justify-content-center align-items-center">
                    <img class="rounded-3" src="${data.objeto.portada}" alt="Portada de pelicula" width="200"/>
                </div>
            </div>
        </div>
    `
}

function verFunciones(id){
    localStorage.setItem("verFuncion", id);
    window.location.href = "/funciones";
}


//Realizamos la consulta de la API
fetch("http://localhost:8080/api/v1/funciones/pelicula")
.then(response => response.json()) //la respuesta de la API la tranformamos a json
.then(data =>{
    data.forEach(idpelicula => { //recorremos la lista de ids de peliculas
        fetch(`http://localhost:8080/api/v1/pelicula/${idpelicula}`) //buscamos la id base la id
        .then(response => response.json()) //la respuesta de la API la tranformamos a json
        .then(pelicula =>{
            const cartelera = document.getElementById("idCartelera");
            cartelera.innerHTML += `
                <button class="clicPelicula" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="infoPelicula(${idpelicula})">
                    <h5>${pelicula.objeto.nombrePelicula}</h5>
                    ${validacionDeImagen(pelicula.objeto.portada)}
                </button>
            `
        })
    });
})

function validacionDeImagen(url){
    if(url == null){
        return `
            <div class="d-flex justify-content-center align-items-center card bg-body-tertiary" style="width: 200px;height: 300px;">
                <i class='bi bi-file-earmark-image'></i>
            </div>    
        `
    }
    return `<img class="rounded-3" src=${url} alt="Portada de pelicula" width="200" height="300"/>`;
}