import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoLingua {

    // Define the Space Person alphabet
    private static final Map<Character, Character> spacePersonAlphabet = new HashMap<>();
    static {
        spacePersonAlphabet.put('a', '~');
        spacePersonAlphabet.put('b', '!');
        spacePersonAlphabet.put('c', '@');
        spacePersonAlphabet.put('d', '#');
        spacePersonAlphabet.put('e', '$');
        spacePersonAlphabet.put('f', '%');
        spacePersonAlphabet.put('g', '^');
        spacePersonAlphabet.put('h', '&');
        spacePersonAlphabet.put('i', '*');
        spacePersonAlphabet.put('j', '(');
        spacePersonAlphabet.put('k', ')');
        spacePersonAlphabet.put('l', '-');
        spacePersonAlphabet.put('m', '_');
        spacePersonAlphabet.put('n', '=');
        spacePersonAlphabet.put('o', '+');
        spacePersonAlphabet.put('p', '[');
        spacePersonAlphabet.put('q', ']');
        spacePersonAlphabet.put('r', '{');
        spacePersonAlphabet.put('s', '}');
        spacePersonAlphabet.put('t', ';');
        spacePersonAlphabet.put('u', ':');
        spacePersonAlphabet.put('v', ',');
        spacePersonAlphabet.put('w', '.');
        spacePersonAlphabet.put('x', '<');
        spacePersonAlphabet.put('y', '>');
        spacePersonAlphabet.put('z', '?');
    }

    // Convert English string to Space Person string
    private static String convertToSpacePerson(String inputString) {
        StringBuilder spacePersonString = new StringBuilder();
        for (char c : inputString.toLowerCase().toCharArray()) {
            if (spacePersonAlphabet.containsKey(c)) {
                spacePersonString.append(spacePersonAlphabet.get(c));
            } else {
                spacePersonString.append(c);
            }
        }
        return spacePersonString.toString();
    }

    // Calculate SHA256 hash
    private static String calculateSHA256(String inputString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(inputString.getBytes());
        StringBuilder hashValue = new StringBuilder();

        for (byte b : hashBytes) {
            hashValue.append(String.format("%02x", b));
        }

        return hashValue.toString();
    }

    // Perform Caesar cipher with a 5-character shift
    private static String caesarCipher(String inputString) {
        StringBuilder result = new StringBuilder();
        for (char c : inputString.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(c)) {
                char shiftedChar = (char) (((c - 'a' + 5) % 26) + 'a');
                result.append(shiftedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Brute force all 0-25 shifts
    private static Map<Integer, String> bruteForceCaesarCipher(String inputString) {
        Map<Integer, String> results = new HashMap<>();
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder shiftedText = new StringBuilder();
            for (char c : inputString.toLowerCase().toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    char shiftedChar = (char) (((c - 'a' + shift) % 26) + 'a');
                    shiftedText.append(shiftedChar);
                } else {
                    shiftedText.append(c);
                }
            }
            results.put(shift, shiftedText.toString());
        }
        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter an English string: ");
        String englishString = scanner.nextLine();

        // Convert to Space Person string
        String spacePersonString = convertToSpacePerson(englishString);
        System.out.println("Space Person String: " + spacePersonString);

        try {
            // Calculate SHA256 hash
            String hashValue = calculateSHA256(spacePersonString);
            System.out.println("SHA256 Hash Value: " + hashValue);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error calculating SHA256 hash: " + e.getMessage());
        }

        // Caesar cipher with a 5-character shift
        String caesarCipherText = caesarCipher(englishString);
        System.out.println("Caesar Cipher (5-character shift): " + caesarCipherText);

        // Brute force all 0-25 shifts
        Map<Integer, String> bruteForceResults = bruteForceCaesarCipher(englishString);
        System.out.println("Brute Force Caesar Cipher Results:");
        for (Map.Entry<Integer, String> entry : bruteForceResults.entrySet()) {
            System.out.println("Shift " + entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }
}
