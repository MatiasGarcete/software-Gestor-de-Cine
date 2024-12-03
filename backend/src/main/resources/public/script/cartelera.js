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
                <div>
                    <h5>${pelicula.objeto.nombrePelicula}</h5>
                    ${validacionDeImagen(pelicula.objeto.portada)}
                </div>
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