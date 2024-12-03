const loginFrom = document.getElementById("formLogin");
loginFrom.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página
    const correo = document.getElementById('correo').value;
    const password = document.getElementById('password').value;

    console.log(correo + " " + password)
    try {
        const response = await fetch('http://localhost:8080/api/v1/usuario/login', {
            method: 'POST', // Usar POST para login
            headers: {
                'Content-Type': 'application/json' // Asegúrate de que el cuerpo sea JSON
            },
            body: JSON.stringify({
                correo: correo,
                password: password
            })
        });
        
        if (response.ok) { // Verifica que la respuesta sea exitosa (200-299)
            const data = await response.json();
            localStorage.setItem("usuario", JSON.stringify(data.objeto));
            window.location.href = "/";
            console.log('Login exitoso', data);
        } else {
            console.error('Error en la solicitud:', response.status, response.statusText);
        }

    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
});
