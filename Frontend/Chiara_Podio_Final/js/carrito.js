function obtenerCarrito() {
    return JSON.parse(localStorage.getItem("carrito")) || [];
}

function guardarCarrito(carrito) {
    localStorage.setItem("carrito", JSON.stringify(carrito));
}

const contenedor = document.getElementById("carrito-contenido");
let carrito = obtenerCarrito();

if (carrito.length === 0) {
    contenedor.classList.add("carrito-vacio-activo");
    contenedor.innerHTML = `
        <h2 class="carrito-vacio-titulo">Tu carrito está vacío</h2>

        <div class="text-center mt-4">
            <a href="tienda.html" class="btn btn-agregar">Agregar Productos</a>
        </div>
    `;
} else {
    renderizarCarrito();
}

function formatearPrecio(valor) {
    return valor.toLocaleString("es-AR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}


function renderizarCarrito() {
    contenedor.innerHTML = `
    <div class="carrito-wrapper">
        <h1 class="titulo-carrito mb-4">Tu carrito</h1>
    </div>
`;

const wrapper = document.querySelector(".carrito-wrapper");


    let subtotal = 0;

    carrito.forEach((item, index) => {
        const precioTotal = item.precio * item.cantidad;
        subtotal += precioTotal;

        const precioTotalFormateado = formatearPrecio(precioTotal);


        wrapper.innerHTML += `
        <div class="carrito-item row align-items-center">

            <!-- Imagen -->
            <div class="col-3 col-md-2">
                <img src="imagenes/${item.imagen}" alt="${item.nombre}" class="img-fluid rounded">
            </div>

            <!-- Nombre y marca -->
            <div class="col-9 col-md-4">
                <h5 class="mb-1">${item.nombre}</h5>
                <p class="text-muted mb-0">${item.marca}</p>
            </div>

            <!-- Cantidad -->
            <div class="col-6 col-md-3 mt-3 mt-md-0 d-flex align-items-center justify-content-md-center gap-2">
                <button class="btn-restar" data-index="${index}">−</button>
                <span class="carrito-cant-num">${item.cantidad}</span>
                <button class="btn-sumar" data-index="${index}">+</button>
            </div>

            <!-- Precio -->
            <div class="col-6 col-md-2 mt-3 mt-md-0 text-md-end carrito-precio">
            $${precioTotalFormateado}
            </div>

            <!-- Eliminar -->
            <div class="col-12 col-md-1 mt-3 mt-md-0 text-md-end">
                <button class="btn-eliminar" data-index="${index}">🗑️</button>
            </div>

        </div>
        `;
    });

    const subtotalFormateado = formatearPrecio(subtotal);

wrapper.innerHTML += `
    <div class="carrito-subtotal mt-4">
        <p><strong>Subtotal:</strong> $${subtotalFormateado}</p>
    </div>

        <!-- LOGIN -->
        <div class="carrito-login mt-4">
            <input id="login-email" type="email" placeholder="Correo electrónico" class="carrito-input">
            <input id="login-password" type="password" placeholder="Contraseña" class="carrito-input">

            <p class="carrito-msg mt-2">
                ¿Todavía no estás suscrito? 
                <a href="suscribirse.html" class="carrito-link">¡Crea una cuenta</a>
                para comprar nuestros productos y recibir las mejores recetas directamente en tu bandeja de entrada!
            </p>
        </div>

        <button id="btn-pagar" class="carrito-pagar btn btn-dark btn-lg mt-3">Pagar</button>
    `;

    activarBotonesCantidad();
    activarBotonesEliminar();
    activarBotonPagar();
}

function activarBotonesCantidad() {
    document.querySelectorAll(".btn-sumar").forEach(btn => {
        btn.addEventListener("click", () => {
            let i = btn.dataset.index;
            carrito[i].cantidad++;
            guardarCarrito(carrito);
            renderizarCarrito();
        });
    });

    document.querySelectorAll(".btn-restar").forEach(btn => {
        btn.addEventListener("click", () => {
            let i = btn.dataset.index;
            if (carrito[i].cantidad > 1) {
                carrito[i].cantidad--;
            }
            guardarCarrito(carrito);
            renderizarCarrito();
        });
    });
}

function activarBotonesEliminar() {
    document.querySelectorAll(".btn-eliminar").forEach(btn => {
        btn.addEventListener("click", () => {
            let i = btn.dataset.index;

            carrito.splice(i, 1);
            guardarCarrito(carrito);
            renderizarCarrito();
        });
    });
}

/* Botón pagar */

function activarBotonPagar() {
    const btnPagar = document.getElementById("btn-pagar");
    btnPagar.addEventListener("click", procesarPago);
}

async function procesarPago() {
    const email = document.getElementById("login-email").value.trim();
    const password = document.getElementById("login-password").value.trim();

    if (!email || !password) {
        alert("Por favor, ingresa tu correo y contraseña.");
        return;
    }

    try {
        // Obtener todos los suscriptores
        const resp = await fetch("http://localhost:8080/suscriptores");
        const suscriptores = await resp.json();

        // Buscar usuario por email y password
        const suscriptor = suscriptores.find(s => 
    s.correo_electronico === email && s.contrasena === password
);

if (!suscriptor) {
    alert("Correo o contraseña incorrectos.");
    return;
}

const ventaDTO = {
    idSuscriptor: suscriptor.id_suscriptor,
    detalleVenta: carrito.map(item => ({
        idProducto: item.id,
        cantidad: item.cantidad
    }))
};


        // Enviar venta
        const respVenta = await fetch("http://localhost:8080/ventas/crear", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(ventaDTO)
        });

        if (!respVenta.ok) {
            alert("Error al generar la venta.");
            return;
        }

        alert("¡Venta realizada con éxito!");
        localStorage.removeItem("carrito");
        window.location.href = "index.html";

    } catch (error) {
        console.error("Error al procesar el pago:", error);
        alert("Hubo un problema al conectar con el servidor.");
    }
}

