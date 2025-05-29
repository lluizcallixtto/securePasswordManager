import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class PasswordLeakChecker {
    public static boolean isPasswordLeaked(String password) {
        try {
            String sha1 = sha1Hex(password).toUpperCase();
            String prefix = sha1.substring(0, 5);
            String suffix = sha1.substring(5);

            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefix);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(suffix)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao verificar vazamento: " + e.getMessage());
            return false;
        }
    }

    private static String sha1Hex(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(input.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
