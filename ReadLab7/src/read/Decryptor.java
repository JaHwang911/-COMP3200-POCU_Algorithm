package read;

import java.util.ArrayList;
import java.util.HashMap;
public class Decryptor {

    private final TrieNode root;

    private final int wordCount;

    public Decryptor(final String[] codeWords) {
        assert (codeWords != null);

        this.wordCount = codeWords.length;

        this.root = new TrieNode();

        for (String word : codeWords) {
            buildTrieRecursive(this.root, word, 0);
        }
    }

    private void buildTrieRecursive(final TrieNode node, final String word, final int index) {
        assert (node != null && word != null && index >= 0);

        if (index == word.length()) {
            node.setEnd();
            return;
        }

        ArrayList<TrieNode> nextNodes = node.getNextCharacters();

        int i;
        for (i = 0; i < nextNodes.size(); ++i) {
            if (nextNodes.get(i).getCharacter() == getLowerCase(word.charAt(index))) {
                buildTrieRecursive(nextNodes.get(i), word, index + 1);
                return;
            }
        }

        node.addCharacter(getLowerCase(word.charAt(index)));
        buildTrieRecursive(node.getNextCharacters().get(i), word, index + 1);
    }

    public String[] findCandidates(final String word) {
        assert (word != null);

        if (this.root.getNextCharacters().size() == 0) {
            return new String[0];
        }

        ArrayList<String> candidates = new ArrayList<String>(this.wordCount);
        HashMap<Character, Integer> remainingVisitCounts = new HashMap<Character, Integer>(word.length());

        for (int i = 0; i < word.length(); ++i) {
            char currChar = getLowerCase(word.charAt(i));
            remainingVisitCounts.put(currChar, remainingVisitCounts.getOrDefault(currChar, 0) + 1);
        }

        char[] chars = new char[word.length()];
        findCandidatesRecursive(this.root, word, remainingVisitCounts, chars, 0, candidates);

        return candidates.toArray(new String[candidates.size()]);
    }

    private void findCandidatesRecursive(final TrieNode node,
                                         final String word,
                                         final HashMap<Character, Integer> remainingVisitCounts,
                                         final char[] chars,
                                         final int size,
                                         final ArrayList<String> outCandidates) {

        assert (node != null && word != null && remainingVisitCounts != null && chars != null && size >= 0 && outCandidates != null);

        if (size == word.length()) {

            if (!node.isKeyEnd()) {
                return;
            }

            outCandidates.add(new String(chars));

            return;
        }

        for (TrieNode nextNode : node.getNextCharacters()) {

            if (remainingVisitCounts.get(nextNode.getCharacter()) == null || remainingVisitCounts.get(nextNode.getCharacter()) == 0) {
                continue;
            }

            remainingVisitCounts.put(nextNode.getCharacter(), remainingVisitCounts.get(nextNode.getCharacter()) - 1);

            chars[size] = nextNode.getCharacter();

            findCandidatesRecursive(nextNode, word, remainingVisitCounts, chars, size + 1, outCandidates);

            remainingVisitCounts.put(nextNode.getCharacter(), remainingVisitCounts.get(nextNode.getCharacter()) + 1);
        }
    }

    private char getLowerCase(final char ch) {
        assert (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z');

        final int CASE_FLAG = 0x20;

        return (char) (ch | CASE_FLAG);
    }
}