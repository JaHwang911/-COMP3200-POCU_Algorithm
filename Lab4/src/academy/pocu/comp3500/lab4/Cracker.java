package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.zip.CRC32;

public final class Cracker {
    private final User[] userTable;
    private final String email;
    private final String password;
    private HashType hashType;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    private void setHashType() {
        User myInfoInUserTable = null;

        for (User user : this.userTable) {
            if (user.getEmail().equals(this.email)) {
                myInfoInUserTable = user;
                break;
            }
        }

        assert (myInfoInUserTable != null);

        String crc32Hash = getCRC32Hash(this.password);

        if (myInfoInUserTable.getPasswordHash().equals(crc32Hash)) {
            this.hashType = HashType.CRC32;
            return;
        }

        String md2Hash = getMD2Hash(this.password);

        if (myInfoInUserTable.getPasswordHash().equals(md2Hash)) {
            this.hashType = HashType.MD2;
            return;
        }

        String md5Hash = getMD5Hash(this.password);

        if (myInfoInUserTable.getPasswordHash().equals(md5Hash)) {
            this.hashType = HashType.MD5;
            return;
        }

        String sha1Hash = getSHA1Hash(this.password);

        if (myInfoInUserTable.getPasswordHash().equals(sha1Hash)) {
            this.hashType = HashType.SHA1;
            return;
        }

        String sha256Hash = getSHA256Hash(this.password);

        if (myInfoInUserTable.getPasswordHash().equals(sha256Hash)) {
            this.hashType = HashType.SHA256;
            return;
        }

        this.hashType = HashType.NONE;
    }

    private static String getCRC32Hash(String password) {
        CRC32 crc32 = new CRC32();
        crc32.update(password.getBytes(StandardCharsets.UTF_8));

        long result = crc32.getValue();

        return Long.toString(result);
    }

    private static String getMD2Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getMD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSHA1Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSHA256Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];
        RainbowTable rainbowTable;

        if (this.hashType == null) {
            setHashType();
        }

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
            case NONE:
                return result;
            default:
                assert (false) : "Unknown hash type";
                return result;
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