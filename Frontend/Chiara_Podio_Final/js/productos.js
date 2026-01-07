// Asignar imágenes según nombre del producto
const imagenesPorProducto = {
    "Libro Recetas de la Abuela": ["libro1.jpg", "libro2.jpg"],
    "Delantal Floreado Vintage (talle único)": ["delantal1.jpg", "delantal2.jpg"],
    "Set de Cucharas Medidoras": ["cucharas-med1.jpg", "cucharas-med2.jpg"],
    "Set Especias de Cocina Premium (x5 60g)": ["condimentos1.jpg", "condimentos2.jpg"],
    "Tabla de Picar de Bambú": ["tabla-picar1.jpg", "tabla-picar2.jpg"],
    "Set Platos de Entrada de Porcelana (x6)": ["plato1.jpg", "plato2.jpg"],
    "Juego de Té de Porcelana": ["juego-te1.jpg", "juego-te2.jpg"]
};

let todosLosProductos = []; 

function renderizarProductos(lista) {
    const row = document.getElementById("productos-row");
    row.innerHTML = "";

    if (lista.length === 0) {
        row.innerHTML = `
    <div class="col-12 text-center py-5">
        <h5 class="mb-2">Sin resultados</h5>
        <p class="text-muted">
            No existen productos que coincidan con lo que estás buscando.
        </p>
    </div>
`;
        return;
    }

    lista.forEach(prod => {
        const imagenes = imagenesPorProducto[prod.nombre] || ["sin-foto.jpg", "sin-foto.jpg"];

        const card = document.createElement("div");
        card.className = "col-12 col-sm-6 col-lg-6 d-flex";

        card.innerHTML = `
            <div class="producto-card position-relative p-3 rounded">

                <div class="img-container">
                    <img src="imagenes/${imagenes[0]}" 
                        class="img-fluid imagen-producto" 
                        data-img1="${imagenes[0]}" 
                        data-img2="${imagenes[1]}"
                    >
                </div>

                <h4 class="mt-3 nombre-producto">${prod.nombre}</h4>

                ${prod.stock === 0 ? `
                    <span class="agotado-badge mb-2 d-inline-block">Agotado</span>
                ` : ""}

                <p class="precio-producto">$${prod.precioUnitarioActual}</p>
            </div>
        `;

        const imagen = card.querySelector(".imagen-producto");

        imagen.addEventListener("click", () => {
            window.location.href = `productoelegido.html?id=${prod.codigo_producto}`;
        });

        imagen.addEventListener("mouseenter", () => {
            imagen.style.transform = "scale(1.08)";
            imagen.src = `imagenes/${imagen.dataset.img2}`;
        });

        imagen.addEventListener("mouseleave", () => {
            imagen.style.transform = "scale(1)";
            imagen.src = `imagenes/${imagen.dataset.img1}`;
        });

        row.appendChild(card);
    });
}


// Cargar productos desde el backend
fetch("http://localhost:8080/productos")
    .then(res => res.json())
    .then(productos => {

        // guardo
        todosLosProductos = productos;

        renderizarProductos(productos);

        // activo buscador
        document.getElementById("buscador").addEventListener("input", e => {
            const texto = e.target.value.toLowerCase().trim();

            const filtrados = todosLosProductos.filter(prod =>
                prod.nombre.toLowerCase().includes(texto)
            );

            renderizarProductos(filtrados);
        });
    })
    .catch(err => console.error("Error cargando productos:", err));



