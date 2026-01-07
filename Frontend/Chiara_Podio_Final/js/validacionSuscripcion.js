document.getElementById("formSuscripcion").addEventListener("submit", function (event) {
    event.preventDefault();

    // Captura de datos
    const nombre = document.getElementById("nombre").value.trim();
    const apellido = document.getElementById("apellido").value.trim();
    const fechaNacimiento = document.getElementById("fecha_nacimiento").value;
    const celular = document.getElementById("celular").value.trim();
    const correo = document.getElementById("correo_electronico").value.trim();
    const contrasena = document.getElementById("contrasena").value.trim();
    const provincia = document.getElementById("provincia").value;
    const ciudad = document.getElementById("ciudad").value.trim();
    const direccion = document.getElementById("direccion").value.trim();
    const preferencia = document.querySelector("input[name='preferencia_receta']:checked");
    const recibirOfertas = document.getElementById("recibir_ofertas").checked;

    // Validaciones
    if (!nombre || !apellido || !correo || !contrasena || !fechaNacimiento || !celular || !ciudad || !direccion) {
        alert("Por favor completá todos los datos obligatorios.");
        return;
    }

    if (isNaN(celular) || celular.length < 7) {
        alert("El número de celular es inválido.");
        return;
    }

    const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
    if (!emailRegex.test(correo)) {
        alert("Correo electrónico inválido.");
        return;
    }

    if (!provincia) {
        alert("Seleccioná una provincia.");
        return;
    }

    if (!preferencia) {
        alert("Seleccioná una preferencia de recetas.");
        return;
    }

    // Armado del objeto que espera el backend
    const suscriptorDTO = {
        nombre: nombre,
        apellido: apellido,
        fecha_nacimiento: fechaNacimiento,
        contrasena: contrasena,
        celular: Number(celular),
        correo_electronico: correo,
        provincia: provincia,
        ciudad: ciudad,
        direccion: direccion,
        preferenciaDeReceta: preferencia.value,
        recibir_ofertas: recibirOfertas
    };

    console.log("Enviando:", suscriptorDTO);

    // Envío al backend
    fetch("http://localhost:8080/suscriptor/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(suscriptorDTO)
    })
    .then(r => {
        if (!r.ok) throw new Error("Error en la API");
        return r.text();
    })
    .then(data => {
        alert("¡Suscripción realizada con éxito!");
        document.getElementById("formSuscripcion").reset();
        window.location.href = "tienda.html";
    })
    .catch(err => {
        alert("No se pudo completar la suscripción.");
        console.error(err);
    });
});
