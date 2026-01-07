// Funciones de carrito 
function obtenerCarrito() {
    return JSON.parse(localStorage.getItem("carrito")) || [];
}
function guardarCarrito(carrito) {
    localStorage.setItem("carrito", JSON.stringify(carrito));
}
function agregarProductoAlCarrito(producto, cantidad, imagen) {
    let carrito = obtenerCarrito();
    const existente = carrito.find(item => item.id === producto.codigo_producto);
    if (existente) {
        existente.cantidad += cantidad;
    } else {
        carrito.push({
            id: producto.codigo_producto,
            nombre: producto.nombre,
            marca: producto.marca,
            precio: producto.precioUnitarioActual,
            imagen: imagen,
            cantidad: cantidad
        });
    }
    guardarCarrito(carrito);
    alert("Producto agregado al carrito");
}



// Obtener el ID desde la URL
const params = new URLSearchParams(window.location.search);
const idProducto = params.get("id");

// Contenedor donde se va a renderizar el detalle
const contenedor = document.getElementById("producto-detalle");

//Imágenes
const imagenesPorProducto = {
    "Libro Recetas de la Abuela": ["libro1.jpg", "libro2.jpg"],
    "Set de Cucharas Medidoras": ["cucharas-med1.jpg", "cucharas-med2.jpg"],
    "Set Especias de Cocina Premium (x5 60g)": ["condimentos1.jpg", "condimentos2.jpg"],
    "Delantal Floreado Vintage (talle único)": ["delantal1.jpg", "delantal2.jpg"],
    "Tabla de Picar de Bambú": ["tabla-picar1.jpg", "tabla-picar2.jpg"],
    "Set Platos de Entrada de Porcelana (x6)": ["plato1.jpg", "plato2.jpg"],
    "Juego de Té de Porcelana": ["juego-te1.jpg", "juego-te2.jpg"]
};


fetch(`http://localhost:8080/producto/${idProducto}`)
    .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
    })
    .then(producto => {

        const imgs = imagenesPorProducto[producto.nombre] || ["sin-foto.jpg", "sin-foto.jpg"];

        contenedor.innerHTML = `
        <!-- COLUMNA IZQUIERDA-->
        <div class="col-12 col-lg-6 position-relative">

 <!-- BOTÓN VOLVER -->
    <a href="tienda.html" class="btn btn-light btn-volver d-inline-flex align-items-center mb-3">
        <i class="bi bi-arrow-left "></i> Agregar más productos
    </a>

            <div class="carousel-detalle">
                <div id="carouselProducto" class="carousel slide">
                    <div class="carousel-inner">

                        <div class="carousel-item active">
                            <img src="imagenes/${imgs[0]}" class="d-block w-100 img-carousel-detalle" id="img-principal">
                        </div>

                        <div class="carousel-item">
                            <img src="imagenes/${imgs[1]}" class="d-block w-100 img-carousel-detalle">
                        </div>
                    </div>

                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselProducto" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselProducto" data-bs-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </button>
                </div>
            </div>
        </div>

        <!-- COLUMNA DERECHA -->
        <div class="col-12 col-lg-6 p-3">

            <h1 class="fw-bold mb-3">${producto.nombre}</h1>

            <p class="precio-detalle mb-2">$${producto.precioUnitarioActual}</p>

            <p class="marca-detalle mb-4">Marca: <strong>${producto.marca || 'N/A'}</strong></p>

            <!-- Descripción -->
            <div class="descripcion-detalle mt-4">
                <p>${producto.descripcion || ''}</p>
            </div>

            <!-- Contador -->
            <div class="d-flex align-items-center mb-4" id="contador-producto">
                <button class="btn btn-outline-secondary" id="btn-menos">−</button>
                <span class="mx-3 fs-5" id="cantidad-span">1</span>
                <button class="btn btn-outline-secondary" id="btn-mas">+</button>
            </div>

            <!-- Botones -->
            <div class="d-grid gap-3 mb-4">
                <button class="btn btn-dark btn-lg w-100" id="btn-agregar">Agregar al carrito</button>
                <button class="btn btn-primary btn-lg w-100 boton-comprar-ahora" id="btn-comprar">Comprar ahora</button>
            </div>

        </div>
        `;

        const btnMenos = document.getElementById("btn-menos");
        const btnMas = document.getElementById("btn-mas");
        const spanCantidad = document.getElementById("cantidad-span");
        const btnAgregar = document.getElementById("btn-agregar");
        const btnComprarAhora = document.getElementById("btn-comprar");

        let cantidad = 1;

        if (btnMas && btnMenos && spanCantidad) {
            btnMas.addEventListener("click", () => {
                cantidad++;
                spanCantidad.textContent = cantidad;
            });

            btnMenos.addEventListener("click", () => {
                if (cantidad > 1) {
                    cantidad--;
                    spanCantidad.textContent = cantidad;
                }
            });
        }

        if (btnAgregar) {
            btnAgregar.addEventListener("click", () => {

            if (producto.stock <= 0) {
                alert("No hay stock suficiente de este producto");
            return;
                    }
                agregarProductoAlCarrito(producto, cantidad, imgs[0]);
            });
        }

        if (btnComprarAhora) {
            btnComprarAhora.addEventListener("click", () => {

            if (producto.stock <= 0) {
                alert("No hay stock suficiente de este producto");
            return;
                }
                
                agregarProductoAlCarrito(producto, cantidad, imgs[0]);
                // redirigimos al carrito con delay para que el guardado termine
                setTimeout(() => window.location.href = "carrito.html", 200);
            });
        }

    })
    .catch(err => {
        contenedor.innerHTML = `<p class="text-danger">Error cargando el producto: ${err.message}</p>`;
        console.error(err);
    });



