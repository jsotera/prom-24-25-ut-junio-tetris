<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tetris UI</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="contenedor">
    <div class="tablero">

        <#assign filas=tablero?size>
        <#assign columnas=tablero[0]?size>

        <#list 0..filas-1 as fila>

            <div class="fila">

            
            <#list 0..columnas-1 as columna>

                <#if tablero[fila][columna]??>
                    <div class="columna ${tablero[fila][columna]}"></div>
                <#else>
                    <div class="columna"></div>
                </#if>
                

            </#list>

            </div>

        </#list>

    </div>

    <div class="estadisticas">
        <#list contadorPiezas as piezaCantidad>
            <div>${piezaCantidad.pieza.letra} se repite ${piezaCantidad.cantidad} de veces</div>
        </#list>
    </div>
</div>

<script>
    document.addEventListener('keydown', function(event) {
      const tecla = event.key.toUpperCase();
      const teclasValidas = ['W', 'A', 'S', 'D', 'R'];

      if (teclasValidas.includes(tecla)) {
        // Crear el formulario
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/tecla-pulsada';

        // Crear el campo oculto con el valor de la tecla
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'tecla';
        input.value = tecla;

        form.appendChild(input);
        document.body.appendChild(form);

        // Enviar el formulario
        form.submit();
      }
    });
</script>

</body>
</html>