package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public final class Cracker {
    static final int OUTPUT_KEY_LENGTH_IN_BYTES = 32;
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        setPassword(password);
    }

    private void setPassword(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            this.password = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];
        int hashIndex;

        for (int i = 0; i < rainbowTables.length; ++i) {
            for (int j = 0; j < this.userTable.length; ++j) {
                String value = rainbowTables[i].get(this.userTable[j].getPasswordHash());

                if (value != null) {
                    result[j] = value;
                }
            }
        }

        return result;
    }
}