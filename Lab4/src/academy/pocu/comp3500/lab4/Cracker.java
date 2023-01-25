package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public final class Cracker {
    private final User[] userTable;
    private final HashType hashType;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;

        User myInfoInUserTable = null;
        String myPasswordMD5Hash = getMD5Hash(password);

        for (User user : userTable) {
            if (user.getEmail().equals(email)) {
                myInfoInUserTable = user;
                break;
            }
        }

        assert (myInfoInUserTable != null);

        if (myInfoInUserTable.getPasswordHash().equals(myPasswordMD5Hash)) {
            this.hashType = HashType.MD5;
            return;
        }

        final int PASSWORD_HASH_LENGTH = myInfoInUserTable.getPasswordHash().length();

        switch (PASSWORD_HASH_LENGTH) {
            case 9:
                this.hashType = HashType.CRC32;
                break;
            case 24:
                this.hashType = HashType.MD2;
                break;
            case 28:
                this.hashType = HashType.SHA1;
                break;
            case 44:
                this.hashType = HashType.SHA256;
                break;
            default:
                this.hashType = HashType.NONE;
                assert (false) : "Can't get hash type";
                break;
        }
    }

    private String getMD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        assert (this.hashType != HashType.NONE);

        String[] result = new String[userTable.length];
        RainbowTable rainbowTable;

        switch (this.hashType) {
            case CRC32:
                rainbowTable = rainbowTables[0];
                break;
            case MD2:
                rainbowTable = rainbowTables[1];
                break;
            case MD5:
                rainbowTable = rainbowTables[2];
                break;
            case SHA1:
                rainbowTable = rainbowTables[3];
                break;
            case SHA256:
                rainbowTable = rainbowTables[4];
                break;
            default:
                assert (false) : "Invalid hash type";
                return null;
        }

        for (int i = 0; i < this.userTable.length; ++i) {
            String plainText = rainbowTable.get(this.userTable[i].getPasswordHash());

            if (plainText != null) {
                result[i] = plainText;
            }
        }

        return result;
    }
}