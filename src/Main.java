import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Gerenciador de Senhas Seguras!");

        if (!LoginManager.login(scanner)) {
            System.out.println("Autenticação falhou. Encerrando...");
            return;
        }

        PasswordManager.menu(scanner);
        scanner.close();



    }
}