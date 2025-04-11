# Proyecto: Validador Concurrente de Contraseñas - Aransy García

## Descripción 📖

Aplicación de consola en Java que permite validar múltiples contraseñas simultáneamente utilizando **concurrencia** e **hilos**.  
Se aplican expresiones regulares para garantizar que cada contraseña cumpla con los siguientes requisitos de seguridad:

- ✅ Longitud mínima de 8 caracteres
- ✅ Al menos un carácter especial
- ✅ Mínimo dos letras mayúsculas
- ✅ Mínimo tres letras minúsculas
- ✅ Al menos un número

Los resultados se muestran en consola y se guardan automáticamente en el archivo `validacion_resultados.txt`.

## Funcionalidades 🌟

- Validación de múltiples contraseñas ingresadas por el usuario.
- Validaciones concurrentes mediante múltiples hilos.
- Reporte visual detallado en consola.
- Guardado automático de los resultados en un archivo externo.
- Manejo de errores y validaciones claras.

## Tecnologías utilizadas 🛠️

- Java SE 17+
- Programación Concurrente
- Expresiones Regulares
- Manejo de Archivos

## Estructura del Proyecto 📂
├── PasswordValidatorApp.java # Clase principal que gestiona la aplicación 
    ├── PasswordValidator.java # Clase encargada de validar cada contraseña 
          ├── validacion_resultados.txt # Archivo de salida con los resultados de las validaciones
              └── README.md # Este documento
              
