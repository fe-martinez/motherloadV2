# Que cosas importantes faltan implementar! (Domingo 4/12)
* Hacer algo con los bordes del mapa. Poner otra imagen o no se.
* Musica, sonidos. (Clari/Both)
* Terminar config (Fran)
* Revisar Flywheel y Controller. Con controller se puede dar la opcion de customizar los movimientos. (Both)
* Ver de crear un método actualizar para las tiendas. Una cosa es inicializar el pane y el root y todo eso, y otra cosa es actualizar los valores según el estado actual del personaje :P. (Clari)
* Hacer las alertas (Clari)

# EXTRA 
- Pausa (ya tenemos el menu igual, creo que con eso alcanza).
- Darle historia al juego.
- Apartado configuracion:
    - Resolución de la ventana.
    - Volumen de la musica y sonidos.
    - Dificultad (yo pensaba en que la dificultad sea el tamaño del mapa, maybe cantidad de minerales).
- Save y Load.
- Zoom in y Zoom out + Resize de la ventana
- Slots de guardados.
- Mejorar el inventario

# TERMINADO
* Probar que la nueva implementacion de movimiento con los estados funcione correctamente.
* Que la estacion de reparacion funcione.
* Que estacion de venta funcione bien. Ahora lo hace pero tiene errores cuando entras y salis. El problema es la funcion que cuenta.
* Pasar todas las tiendas a root en vez de popup (algunas ya están)
* Leer todo el código, hay muchos cambios por todos lados y anotaciones.
* Agregar la opción de Explosivos, existe la clase pero no está en el HashMap de la TiendaDeConsumibles y nos puede generar problemas!!
* Tiendas:
- Estación de Venta: ya casi está, no más que no llama a interactuar JAJAJAJAJAJA está vendiendo ahí de una. Creo que está mal eso también, tendría que llamar a interactuar de su correspondiente tienda. Pero es eso no más :P
- Estación de Consumibles: tiene seteados los botones para vender pero no se muestra (por el problema de que todavía no sabemos dónde mostrarla). Tiene el fondo, tiene la X para cerrar, pero le falta acomodar los botones y los label en la pantalla porque se corta la imagen del fondo cuando movés algo. 
- Estación de Servicio: te adjunto la clase que hice aparte porque no me deja mover al personaje JAJAJAJAJAJAJAJJA
- Estación de Reparación: hay que armarla, es igual a la de servicio, mi idea era que hagamos una superclase y dos subclases pero no sé.
- Tienda de Mejoras: hay que ponerle fondo (no me toma el fondo), los botones van bien donde están, pero hay que ponerles los setOnAction(...).
* Afinar animaciones de pj.
- Movimiento.
- Animación correcta del taladro.
* Config file.
* Que la est de venta y la de reparación se actualicen (Fran)
* Que la barrita de vida que aparece arriba se pinte según la vida del personaje (solo funciona cuando hay 100% de vida) (Fran)
* Agregar la mecanica de daño. (Fran)
* Dar una manera de ganar el juego. (Clari)
* Darle estado al juego. (Clari)
* Crear los minerales que faltan :P
