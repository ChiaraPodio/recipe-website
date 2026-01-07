const imagenesPorProducto = {
    "Libro Recetas de la Abuela": ["libro1.jpg", "libro2.jpg"],
    "Delantal Floreado Vintage (talle único)": ["delantal1.jpg", "delantal2.jpg"],
    "Set Especias de Cocina Premium (x5 60g)": ["condimentos1.jpg", "condimentos2.jpg"],
    "Tabla de Picar de Bambú": ["tabla-picar1.jpg", "tabla-picar2.jpg"],
    "Set Platos de Entrada de Porcelana (x6)": ["plato1.jpg", "plato2.jpg"]
};

// Carrusel Promo Tienda
async function cargarCarruselTienda() {
    try {
        const response = await fetch("http://localhost:8080/productos");
        const productos = await response.json();

        const contenedor = document.getElementById("carousel-tienda-inner");

        // productos
        const seleccionados = productos.filter(p => imagenesPorProducto[p.nombre]);


        contenedor.innerHTML = "";

        seleccionados.forEach((prod, i) => {
            const imgs = imagenesPorProducto[prod.nombre] || [];
            const img = imgs[0] ? `imagenes/${imgs[0]}` : "imagenes/default.jpg";

            contenedor.innerHTML += `
                <div class="carousel-item ${i === 0 ? "active" : ""}">
                    <img src="${img}" class="d-block w-100 img-promo-tienda" onclick="window.location.href='tienda.html'">
                </div>

            `;
        });

    } catch (err) {
        console.error("Error cargando productos:", err);
    }
}

document.addEventListener("DOMContentLoaded", cargarCarruselTienda);
