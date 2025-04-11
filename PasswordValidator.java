import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements Runnable {
    private final String password;

    public PasswordValidator(String password) {
        this.password = password;
    }

    @Override
    public void run() {
        StringBuilder result = new StringBuilder();
        result.append("Contraseña: ").append(password).append("\n");

        boolean isValid = true;

        // Longitud mínima
        if (password.length() >= 8) {
            result.append("- ✅ Longitud mínima cumplida.\n");
        } else {
            result.append("- ❌ Longitud mínima de 8 caracteres no cumplida.\n");
            isValid = false;
        }

        // Carácter especial
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
        if (specialCharPattern.matcher(password).find()) {
            result.append("- ✅ Contiene carácter especial.\n");
        } else {
            result.append("- ❌ Falta un carácter especial.\n");
            isValid = false;
        }

        // Mayúsculas
        Matcher upperCaseMatcher = Pattern.compile("[A-Z]").matcher(password);
        int upperCaseCount = 0;
        while (upperCaseMatcher.find()) upperCaseCount++;
        if (upperCaseCount >= 2) {
            result.append("- ✅ Contiene al menos dos letras mayúsculas.\n");
        } else {
            result.append("- ❌ Faltan letras mayúsculas (mínimo 2).\n");
            isValid = false;
        }

        // Minúsculas
        Matcher lowerCaseMatcher = Pattern.compile("[a-z]").matcher(password);
        int lowerCaseCount = 0;
        while (lowerCaseMatcher.find()) lowerCaseCount++;
        if (lowerCaseCount >= 3) {
            result.append("- ✅ Contiene suficientes letras minúsculas.\n");
        } else {
            result.append("- ❌ Faltan letras minúsculas (mínimo 3).\n");
            isValid = false;
        }

        // Números
        if (password.matches(".*\\d.*")) {
            result.append("- ✅ Contiene al menos un número.\n");
        } else {
            result.append("- ❌ Falta un número.\n");
            isValid = false;
        }

        if (isValid) {
            result.append("\u001B[32m=== Contraseña VÁLIDA ===\u001B[0m\n");
        } else {
            result.append("\u001B[31m=== Contraseña INVÁLIDA ===\u001B[0m\n");
        }

        result.append("---------------------------------------------------\n");

        // Mostrar en consola
        System.out.print(result);

        // Guardar en archivo
        guardarEnArchivo(result.toString());
    }

    private synchronized void guardarEnArchivo(String texto) {
        try (PrintWriter out = new PrintWriter(new FileWriter("validacion_resultados.txt", true))) {
            out.print(texto);
        } catch (IOException e) {
            System.out.println("Error escribiendo en el archivo: " + e.getMessage());
        }
    }
}
