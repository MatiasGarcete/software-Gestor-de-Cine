const nav = document.getElementById("idUsuario");

if (!localStorage.getItem("usuario")) {
    nav.innerHTML = `<a class="btn btn-outline-secondary" href="/login">Iniciar Sesión</a>`;
} else {
    const data = JSON.parse(localStorage.getItem('usuario'));
    // Render básico inicial
    nav.innerHTML = `
    <div id="rol-container" class="d-flex justify-content-center align-items-center"></div>
    <div class="btn-group dropdown" role="group">
        <button type="button" class="btn btn-primary dropdown-toggle d-flex justify-content-center align-items-center" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-person-circle"></i>
            <p class="m-1">${data.nombre}</p>
        </button>
        <ul class="dropdown-menu">
            <li>
                <button class="dropdown-item" onClick="cerrarSession()">Cerrar Sesión</button>
            </li>
        </ul>
    </div>
    `;
    
    // Llamada para agregar el rol dinámicamente
    cargarRol(data.idRol);
}

async function cargarRol(idRol) {
    try {
        const rolContainer = document.getElementById('rol-container');
        const rol = await fetch(`http://localhost:8080/api/v1/rol/${idRol}`).then(res => res.json());

        if (rol && rol.nombreRol === 'admin') {
            rolContainer.innerHTML = `
            <div class="btn-group"">
                <a href="/gestionpelicula" class="btn link btn-primary d-flex justify-content-center align-items-center">
                    <p class="m-0">Gestionar Películas</p>
                </a>
            </div>
            <div class="btn-group"">
                <a href="/gestionfuncion" class="btn link btn-primary d-flex justify-content-center align-items-center">
                    <p class="m-0">Gestionar Funciones</p>
                </a>
            </div>
            `;
        } else {
            rolContainer.innerHTML = ""; // Si no es admin, no muestra nada
        }
    } catch (error) {
        console.error('Error al cargar el rol:', error);
    }
}

function cerrarSession() {
    localStorage.removeItem("usuario");
    window.location.href = "/";
}