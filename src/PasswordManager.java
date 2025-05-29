import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class PasswordManager {
    private static final String FILE = "src/main/resources/senhas.dat";

    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Adicionar senha");
            System.out.println("2. Ver senhas");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    addPassword(scanner);
                    break;
                case "2":
                    viewPasswords();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void addPassword(Scanner scanner) {
        System.out.print("Serviço: ");
        String servico = scanner.nextLine();
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (PasswordLeakChecker.isPasswordLeaked(senha)) {
            System.out.println("⚠️ Essa senha já foi vazada! Use outra.");
            return;
        }

        String entry = servico + ":" + usuario + ":" + senha + "\n";
        try {
            String encrypted = EncryptionUtil.encrypt(entry);
            Files.writeString(Path.of(FILE), encrypted, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Senha salva com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao salvar senha: " + e.getMessage());
        }
    }

    private static void viewPasswords() {
        try {
            String encrypted = Files.readString(Path.of(FILE));
            String decrypted = EncryptionUtil.decrypt(encrypted);
            System.out.println("\nSenhas armazenadas:\n" + decrypted);
        } catch (Exception e) {
            System.err.println("Erro ao ler senhas: " + e.getMessage());
        }
    }

}
