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
            <div class="btn-group" role="group">
                <button type="button" class="menu-btn btn btn-primary dropdown-toggle d-flex justify-content-center align-items-center" data-bs-toggle="dropdown" aria-expanded="false">
                    Gestionar Películas
                </button>
                <ul class="dropdown-menu">
                    <li><button class="dropdown-item">Crear</button></li>
                    <li><a href="/gestion/pelicula" class="dropdown-item">Modificar</a></li>
                    <li><button class="dropdown-item">Eliminar</button></li>
                </ul>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="menu-btn btn btn-primary dropdown-toggle d-flex justify-content-center align-items-center" data-bs-toggle="dropdown" aria-expanded="false">
                    Gestionar Funciones
                </button>
                <ul class="dropdown-menu">
                    <li><button class="dropdown-item">Crear</button></li>
                    <li><button class="dropdown-item">Modificar</button></li>
                    <li><button class="dropdown-item">Eliminar</button></li>
                </ul>
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
    location.reload(true);
}