import java.util.Scanner;

public class LoginManager {
    private static final String USER = "admin";
    private static final String PASS = "1234";

    public static boolean login(Scanner scanner) {
        System.out.print("Usuário: ");
        String user = scanner.nextLine();

        System.out.print("Senha: ");
        String pass = scanner.nextLine();

        if (USER.equals(user) && PASS.equals(pass)) {
            return TwoFactorAuth.verify(scanner);
        }
        return false;
    }
}
