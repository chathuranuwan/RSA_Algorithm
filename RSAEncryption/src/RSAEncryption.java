import java.math.BigInteger;
import java.util.Random;

public class RSAEncryption {
    public static void main(String[] args) {
        // Generate two large prime numbers
        BigInteger p = generateLargePrime();
        BigInteger q = generateLargePrime();

        System.out.println("p: " + p);
        System.out.println("q: " + q);

        // Calculate n (modulus) and phi (Euler's totient function)
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        System.out.println("n: " + n);
        System.out.println("phi: " + phi);

        // Choose a public exponent
        BigInteger e = choosePublicExponent(phi);

        System.out.println("e: " + e);

        // Calculate the private key
        BigInteger d = calculatePrivateKey(phi, e);

        System.out.println("d: " + d);

        String name = "Chathura Nuwan";
        System.out.println("Original Name: " + name);

        // Convert the name to a BigInteger
        BigInteger plaintext = new BigInteger(name.getBytes());

        // Encryption: C = M^e mod n
        BigInteger ciphertext = plaintext.modPow(e, n);
        System.out.println("BigInteger of Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        // Decryption: M = C^d mod n
        BigInteger decryptedText = ciphertext.modPow(d, n);
        String decryptedName = new String(decryptedText.toByteArray());
        System.out.println("Decrypted Name: " + decryptedName);
    }

    // Generates a large prime number using the BigInteger class
    private static BigInteger generateLargePrime() {
        Random rand = new Random();
        return BigInteger.probablePrime(512, rand);
    }

    // Chooses a fixed public exponent value (commonly used: 65537)
    private static BigInteger choosePublicExponent(BigInteger phi) {
        return new BigInteger("65537");
    }

    // Calculates the private key using the extended Euclidean algorithm
    private static BigInteger calculatePrivateKey(BigInteger phi, BigInteger e) {
        return e.modInverse(phi);
    }
}
