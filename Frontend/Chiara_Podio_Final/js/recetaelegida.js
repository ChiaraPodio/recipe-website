document.addEventListener("DOMContentLoaded", cargarReceta);

// Leer ID de la URL 
function cargarReceta() {
    const params = new URLSearchParams(window.location.search);
    const codigo_receta = params.get("codigo_receta");

    if (!codigo_receta) {
        console.error("No se recibió un ID");
        return;
    }

    fetch(`http://localhost:8080/receta/${codigo_receta}`)
        .then(res => res.json())
        .then(receta => mostrarReceta(receta))
        .catch(err => console.error("Error al obtener receta:", err));
        
}

const imagenesPorReceta = {
  "Torta Pastafrola": ["Pastafrola2.jpg", "Pastafrola.jpg", "Pastafrola3.jpg"],
  "Sopa de Carne y Verduras Soyo": ["sopa-soyo.jpg", "sopa-soyo2.jpg", "sopa-soyo3.jpg"],
  "Churros caseros": ["churros.jpg", "churros2.jpg", "churros3.jpg"],
  "Ñoquis de Calabaza con Salsa Rosa": ["noquis.jpg", "noquis2.jpg", "noquis3.jpg"],
  "Torta frita": ["tortafritas3.jpg", "tortafritas.jpg", "tortafritas2.jpg"],
  "Alfajores de maicena": ["alfajores-maicena.jpg", "alfajores-maicena2.jpg", "alfajores-maicena3.jpg"],
  "Canelones de verdura y ricota": ["canelones.jpg", "canelones2.jpg", "canelones3.jpg"],
  "Risotto 4 Quesos": ["risotto.jpg", "risotto2.jpg", "risotto3.jpg"],
  "Budín de limón": ["budinlimon2.jpg", "budinlimon.jpg", "budinlimon3.jpg"],
  "Pastel de papa": ["pasteldepapa.jpg", "pasteldepapa2.jpg", "pasteldepapa3.jpg"],
  "Rolls de Canela": ["rolls-canela.jpg", "rolls-canela2.jpg", "rolls-canela3.jpg"]
};


function mostrarReceta(receta) {

    const contenedor = document.getElementById("receta-completa");

    // Obtener la primera imagen de la receta
    const primeraImagen = imagenesPorReceta[receta.nombre]?.[0] || "default.jpg";

    // Generar listas
    const ingredientesHTML = receta.listaIngredientes.map(ing => `
        <li class="list-group-item">
            ${ing.nombre} — ${ing.cantidad} ${ing.unidad_medida}
        </li>
    `).join("");

    const pasosHTML = receta.listaPasosReceta
        .sort((a,b) => a.numero_paso - b.numero_paso)
        .map(p => `<li>${p.descripcion}</li>`)
        .join("");

    contenedor.innerHTML = `
        <section class="receta-hero">
            <div class="hero-container">

                <div class="hero-text">
                    <span class="tiempo">${receta.tiempo_preparacion} 🕒</span>
                    <h1>${receta.nombre}</h1>
                    <p class="subtitulo">${receta.descripcion}</p>
                </div>

                <div class="hero-img">
                    <img src="imagenes/${primeraImagen}" alt="${receta.nombre}">
                </div>

            </div>
        </section>

        <section class="recetas-abajo seccion-clara">
            <div class="container px-3 px-md-5">

                <div class="row">

                    <div class="col-12 col-md-4 ingredientes-card">
                        <h2>Ingredientes</h2>
                        <ul class="list-group">
                            ${ingredientesHTML}
                        </ul>
                    </div>

                    <div class="col-12 col-md-8">
                        <h2>Preparación</h2>
                        <ul>
                            ${pasosHTML}
                        </ul>
                    </div>

                </div>

            </div>
        </section>
    `;
}


