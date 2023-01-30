package academy.pocu.comp3500.lab5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bank {
    private final String[] encodedPubKeys;
    private final long[] amounts;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        this.encodedPubKeys = new String[pubKeys.length];
        this.amounts = amounts;

        for (int i = 0; i < pubKeys.length; ++i) {
            this.encodedPubKeys[i] = encodeToHexString(pubKeys[i]);
        }
    }

    public long getBalance(final byte[] pubKey) {
        String encodedInputPubKey = encodeToHexString(pubKey);

        for (int i = 0; i < this.encodedPubKeys.length; ++i) {
            if (this.encodedPubKeys[i].equals(encodedInputPubKey)) {
                return this.amounts[i];
            }
        }

        return 0;
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        byte[] transactionHash = getSHA256Hash(encodeToHexString(from) + encodeToHexString(to) + amount);

        return false;
    }

    private byte[] decryptWithPubKey(byte[] transactionHash, String pubKey) {
        return null;
    }

    private static String encodeToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte oneByte : bytes) {
            result.append(String.format("%02x", oneByte));
        }

        return result.toString();
    }

    private static byte[] getSHA256Hash(String transaction) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(transaction.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}