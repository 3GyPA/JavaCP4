// Клас для представлення літери
class Letter {
    private char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

// Клас для представлення слова (масив літер)
class Word {
    private Letter[] letters;

    public Word(String word) {
        letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Letter letter : letters) {
            builder.append(letter);
        }
        return builder.toString();
    }
}

// Клас для представлення розділового знака
class PunctuationMark {
    private char value;

    public PunctuationMark(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

// Клас для представлення речення (масив слів і розділових знаків)
class Sentence {
    private Word[] words;
    private PunctuationMark punctuation;

    public Sentence(String sentence) {
        String trimmed = sentence.trim();
        char punctuationChar = trimmed.charAt(trimmed.length() - 1);
        punctuation = new PunctuationMark(punctuationChar);

        String[] wordStrings = trimmed.substring(0, trimmed.length() - 1).split("\\s+");
        words = new Word[wordStrings.length];
        for (int i = 0; i < wordStrings.length; i++) {
            words[i] = new Word(wordStrings[i]);
        }
    }

    public void swapFirstAndLastWords() {
        if (words.length > 1) {
            Word temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Word word : words) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(word);
        }
        builder.append(punctuation);
        return builder.toString();
    }
}

// Клас для представлення тексту (масив речень)
class Text {
    private Sentence[] sentences;

    public Text(String text) {
        String[] sentenceStrings = text.split("(?<=[.!?])\\s+");
        sentences = new Sentence[sentenceStrings.length];
        for (int i = 0; i < sentenceStrings.length; i++) {
            sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }

    public void processText() {
        for (Sentence sentence : sentences) {
            sentence.swapFirstAndLastWords();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Sentence sentence : sentences) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(sentence);
        }
        return builder.toString();
    }
}

// Головний клас для виконання програми
public class Main {
    public static void main(String[] args) {
        try {
            String text = "   Everybody    Wants to Rule the World was written by Roland Orzabal, Ian Stanley and Chris Hughes, and produced by Hughes. ";

            // Заміна табуляцій і зайвих пробілів одним пробілом
            text = text.replaceAll("\\s+", " ").trim();

            System.out.println("Original Text:");
            System.out.println(text);

            // Створення об'єкта Text і обробка тексту
            Text processedText = new Text(text);
            processedText.processText();

            System.out.println("\nModified Text:");
            System.out.println(processedText);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
