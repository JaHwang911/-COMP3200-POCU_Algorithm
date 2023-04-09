package read;

import java.util.ArrayList;

final class TrieNode {

    private final char DEFAULT_CAPACITY = 8;
    private final char character;
    private boolean isKeyEnd;
    private final ArrayList<TrieNode> nextCharacters;

    TrieNode() {
        this(Character.MAX_VALUE);
    }

    TrieNode(final char character) {

        this.character = character;
        this.nextCharacters = new ArrayList<TrieNode>(DEFAULT_CAPACITY);
    }

    final char getCharacter() {
        return this.character;
    }

    final ArrayList<TrieNode> getNextCharacters() {
        return this.nextCharacters;
    }

    final void addCharacter(final char character) {
        this.nextCharacters.add(new TrieNode(character));
    }

    final boolean isKeyEnd() {
        return this.isKeyEnd;
    }

    final void setEnd() {
        this.isKeyEnd = true;
    }
}