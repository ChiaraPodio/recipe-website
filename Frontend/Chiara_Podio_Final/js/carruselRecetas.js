document.addEventListener("DOMContentLoaded", iniciarCarrusel);

const imagenesPorReceta = { 
    "Torta Pastafrola": ["Pastafrola2.jpg","Pastafrola.jpg","Pastafrola3.jpg"], 
    "Sopa de Carne y Verduras Soyo": ["sopa-soyo.jpg","sopa-soyo2.jpg","sopa-soyo3.jpg"], 
    "Churros caseros": ["churros.jpg","churros2.jpg","churros3.jpg"], 
    "Ñoquis de Calabaza con Salsa Rosa": ["noquis.jpg","noquis2.jpg","noquis3.jpg"], 
    "Torta frita": ["tortafritas3.jpg","tortafritas.jpg","tortafritas2.jpg"], 
    "Alfajores de maicena": ["alfajores-maicena.jpg","alfajores-maicena2.jpg","alfajores-maicena3.jpg"], 
    "Canelones de verdura y ricota": ["canelones.jpg","canelones2.jpg","canelones3.jpg"], 
    "Risotto 4 Quesos": ["risotto.jpg","risotto2.jpg","risotto3.jpg"], 
    "Budín de limón": ["budinlimon2.jpg","budinlimon.jpg","budinlimon3.jpg"], 
    "Pastel de papa": ["pasteldepapa.jpg","pasteldepapa2.jpg","pasteldepapa3.jpg"], 
    "Rolls de Canela": ["rolls-canela.jpg","rolls-canela2.jpg","rolls-canela3.jpg"] };

function iniciarCarrusel() {
    fetch("http://localhost:8080/recetas")
        .then(res => res.json())
        .then(data => {
            const track = document.getElementById("carousel-track");

            data.forEach(receta => {
                const imagen = imagenesPorReceta[receta.nombre]?.[0] || "default.jpg";

                const item = document.createElement("div");

                item.classList.add(
                    "carousel-item-receta",
                    "col-12",
                    "col-sm-6",
                    "col-md-4",
                    "d-flex",
                    "justify-content-center"
                );

                item.innerHTML = `
                    <div class="card-receta">
                        <img class="img-receta" src="imagenes/${imagen}">
                        <div class="card-content">
                            <h3>${receta.nombre}</h3>
                            <p class="desc">${receta.descripcion}</p>
                            <a href="recetaelegida.html?codigo_receta=${receta.codigo_receta}" class="btn-ver">Ver receta</a>
                        </div>
                    </div>
                `;

                track.appendChild(item);

                const imgElement = item.querySelector(".img-receta");

                imgElement.style.cursor = "pointer";
                imgElement.addEventListener("click", () => {
                window.location.href = `recetaelegida.html?codigo_receta=${receta.codigo_receta}`;
});

            });

            iniciarCarruselInfinito(track);
        });
}


function iniciarCarruselInfinito(track) {
    let desplazamiento = 0;

    setInterval(() => {
        const firstCard = track.querySelector(".carousel-item-receta");
        if (!firstCard) return;

        const cardWidth = firstCard.offsetWidth + 20;

        desplazamiento -= cardWidth;

        track.style.transition = "transform 0.5s ease";
        track.style.transform = `translateX(${desplazamiento}px)`;

        setTimeout(() => {
            track.style.transition = "none";  
            track.appendChild(firstCard);     // muevo primera card al final
            desplazamiento += cardWidth;      // corrijo desplazamiento
            track.style.transform = `translateX(${desplazamiento}px)`;
        }, 500);
    }, 2500);
}



