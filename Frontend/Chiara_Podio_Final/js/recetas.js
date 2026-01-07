// Cards recetas
document.addEventListener("DOMContentLoaded", cargarRecetas);

// Array de imágenes locales
const imagenesPorReceta = {
  "Torta Pastafrola": [
    "Pastafrola2.jpg",
    "Pastafrola.jpg",
    "Pastafrola3.jpg"
  ],
  "Sopa de Carne y Verduras Soyo": [
    "sopa-soyo.jpg",
    "sopa-soyo2.jpg",
    "sopa-soyo3.jpg"
  ],
  "Churros caseros": [
    "churros.jpg",
    "churros2.jpg",
    "churros3.jpg"
  ],
  "Ñoquis de Calabaza con Salsa Rosa": [
    "noquis.jpg",
    "noquis2.jpg",
    "noquis3.jpg"
  ],
  "Torta frita": [
    "tortafritas3.jpg",
    "tortafritas.jpg",
    "tortafritas2.jpg"
  ],
  "Alfajores de maicena": [
    "alfajores-maicena.jpg",
    "alfajores-maicena2.jpg",
    "alfajores-maicena3.jpg"
  ],
  "Canelones de verdura y ricota": [
    "canelones.jpg",
    "canelones2.jpg",
    "canelones3.jpg"
  ],
  "Risotto 4 Quesos": [
    "risotto.jpg",
    "risotto2.jpg",
    "risotto3.jpg"
  ],
  "Budín de limón": [
    "budinlimon2.jpg",
    "budinlimon.jpg",
    "budinlimon3.jpg"
  ],
  "Pastel de papa": [
    "pasteldepapa.jpg",
    "pasteldepapa2.jpg",
    "pasteldepapa3.jpg"
  ],
  "Rolls de Canela": [
    "rolls-canela.jpg",
    "rolls-canela2.jpg",
    "rolls-canela3.jpg"
  ]
};


// Cargar recetas iniciales
let recetasOriginales = [];

function cargarRecetas() {
  fetch("http://localhost:8080/recetas")
    .then(response => response.json())
    .then(data => {
      recetasOriginales = data;   
      mostrarRecetas(data);       
      activarBuscador();          
    })
    .catch(error => console.error("Error al cargar recetas:", error));
}


// Mostrar las tarjetas
function mostrarRecetas(recetas) {
  const contenedor = document.getElementById("recetas-container");
  contenedor.innerHTML = "";

   if (recetas.length === 0) {
    contenedor.innerHTML = `
      <div class="col-12 text-center py-5">
        <h5 class="mb-2">Sin resultados</h5>
        <p class="text-muted">
          No se encontraron recetas que coincidan con tu búsqueda.
        </p>
      </div>
    `;
    return;
  }

  recetas.forEach(receta => {
    const card = document.createElement("div");
    card.className = "col-12 col-sm-6 col-md-4 d-flex";

    const imagenes = imagenesPorReceta[receta.nombre] || ["default.jpg"];

    card.innerHTML = `
      <div class="card-receta">
        <img class="img-receta" src="imagenes/${imagenes[0]}" alt="${receta.nombre}">
        <div class="card-content">
          <h3>${receta.nombre}</h3>
          <p class="desc">${receta.descripcion}</p>
          <a href="recetaelegida.html?codigo_receta=${receta.codigo_receta}" class="btn-ver">Ver receta</a>
        </div>
      </div>
    `;

    const imgElement = card.querySelector(".img-receta");
    
imgElement.style.cursor = "pointer";
imgElement.addEventListener("click", () => {
window.location.href = `recetaelegida.html?codigo_receta=${receta.codigo_receta}`;
});

    // Carrusel
    let index = 0;
    let intervalId = null;

    card.addEventListener("mouseenter", () => {
      if (imagenes.length > 1) {
        intervalId = setInterval(() => {
          index = (index + 1) % imagenes.length;

          // Fade out
          imgElement.classList.add("fade-out");

          setTimeout(() => {
            imgElement.src = `imagenes/${imagenes[index]}`;
            imgElement.classList.remove("fade-out");
          }, 250); 
        }, 900);
      }
    });

    card.addEventListener("mouseleave", () => {
      clearInterval(intervalId);
      index = 0;

      imgElement.classList.add("fade-out");

      setTimeout(() => {
        imgElement.src = `imagenes/${imagenes[0]}`;
        imgElement.classList.remove("fade-out");
      }, 250);
    });

    contenedor.appendChild(card);
  });
}


// Buscador
function activarBuscador() {
  const input = document.getElementById("buscador");

  input.addEventListener("input", function () {
    const texto = input.value.toLowerCase().trim();

    const filtradas = recetasOriginales.filter(receta => {
      const nombre = receta.nombre.toLowerCase();
      const categoria = receta.categoria.toLowerCase();

      if (nombre.includes(texto)) return true;
      if (texto === "dulce" && categoria.includes("dulce")) return true;
      if (texto === "salado" && categoria.includes("salada")) return true;
      if (categoria.includes(texto)) return true;

      return false;
    });

    mostrarRecetas(filtradas);
  });
}



