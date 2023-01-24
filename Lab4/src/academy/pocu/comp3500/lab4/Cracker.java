package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];

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