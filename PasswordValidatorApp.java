import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordValidatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> passwords = new ArrayList<>();

        System.out.println("=== Validación Concurrente de Contraseñas ===");
        System.out.print("Ingrese la cantidad de contraseñas a validar: ");
        int cantidad = obtenerEntero(scanner);

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Ingrese la contraseña #" + i + ": ");
            String password = scanner.nextLine();
            passwords.add(password);
        }

        List<Thread> threads = new ArrayList<>();
        for (String password : passwords) {
            PasswordValidator validator = new PasswordValidator(password);
            Thread thread = new Thread(validator);
            threads.add(thread);
            thread.start();
        }

        // Esperamos que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando hilo: " + e.getMessage());
            }
        }

        System.out.println("=== Validación finalizada ===");
        System.out.println("Revisa el archivo 'validacion_resultados.txt' para ver los resultados guardados.");
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número entero válido: ");
            }
        }
    }
}
