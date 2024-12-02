const loginFrom = document.getElementById("formLogin");

loginFrom.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita que el formulario recargue la página
    const correo = document.getElementById('correo').value;
    const password = document.getElementById('password').value;

    console.log(correo + " " + password)
    try {
        const response = await fetch('http://localhost:8080/api/v1/usuario/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ correo, password })
        });
        console.log(response)
    } catch (error) {
        //console.error('Error:', error);
    }
})