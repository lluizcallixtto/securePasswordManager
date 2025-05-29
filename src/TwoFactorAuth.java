import java.util.Random;
import java.util.Scanner;

public class TwoFactorAuth {
    public static boolean verify(Scanner scanner) {
        int code = new Random().nextInt(900000) + 100000;
        System.out.println("Seu código 2FA é: " + code);
        System.out.print("Digite o código: ");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input == code;
    }
}
