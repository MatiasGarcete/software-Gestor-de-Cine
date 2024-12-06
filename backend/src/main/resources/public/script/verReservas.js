llenarDatos()

async function ObtenerInfoReserva(id) {
    const data = await fetch(`http://localhost:8080/api/v1/reserva/${id}`).then(res => res.json())
    const dataFuncion = await fetch(`http://localhost:8080/api/v1/funcion/${data.objeto.idFuncion}`).then(res => res.json())
    const dataPelicula = await fetch(`http://localhost:8080/api/v1/pelicula/${dataFuncion.objeto.idPelicula}`).then(res => res.json())
    console.log(data)
    console.log(dataFuncion)
    return `
        <div class="col">
            <p class="m-3">${dataPelicula.objeto.nombrePelicula}</p>
        </div>
        <div class="col">
            <p class="m-3">${dataFuncion.objeto.fecha}</p>
        </div>
        <div class="col">
            <p class="m-3">${dataFuncion.objeto.hora}</p>
        </div>
    `

}
async function llenarDatos() {
    if (localStorage.getItem("usuario")) {
        const tabla = document.getElementById("tabla");
        const dataUser = JSON.parse(localStorage.getItem("usuario"));
        console.log(dataUser.reservas);

        for (const element of dataUser.reservas) {
            // Espera el resultado de ObtenerInfoReserva
            const infoReserva = await ObtenerInfoReserva(element.idreserva);
            console.log(infoReserva);
            
            // Inserta el contenido en la tabla
            tabla.innerHTML += `
            <div class="container text-center">
                <div class="row d-flex justify-content-center align-items-center bg-primary-subtle border border-primary-subtle rounded-3">
                    <div class="col">
                        <p class="m-3">${element.fechaReserva}</p>
                    </div>
                    ${infoReserva}
                    <div class="col">
                        <p class="m-3">${element.cantidadEntradas}</p>
                    </div>
                    <div class="col">
                        <p class="m-3">$${element.total}</p>
                    </div>
                </div>
            </div>
            `;
        }
    }
}
