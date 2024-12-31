/**
 * Клас Main демонструє використання створених класів для роботи з текстом.
 * Виконує обробку тексту згідно з умовами завдання:
 * - заміна послідовності табуляцій та пробілів одним пробілом;
 * - зміна місцями першого та останнього слова у кожному реченні.
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Заданий текст
            String text = "Everybody Wants to Rule the World was written by Roland Orzabal, Ian Stanley and Chris Hughes, and produced by Hughes.\t\t" +
                    "The song was first released on 18 March 1985.";

            System.out.println("Оригінальний текст:");
            System.out.println(text);

            // Створення об'єкта Text для роботи з текстом
            Text processedText = new Text(text);
            processedText.normalizeSpaces();
            processedText.swapFirstAndLastWords();

            System.out.println("\nОброблений текст:");
            System.out.println(processedText);

        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
        }
    }
}

/**
 * Клас Letter представляє окрему літеру тексту.
 */
class Letter {
    private char value; // Значення літери

    /**
     * Конструктор для створення літери.
     *
     * @param value символ, що представляє літеру.
     */
    public Letter(char value) {
        this.value = value;
    }

    /**
     * Метод для отримання значення літери.
     *
     * @return символ, що представляє літеру.
     */
    public char getValue() {
        return value;
    }
}

/**
 * Клас Word представляє слово, яке складається з масиву літер.
 */
class Word {
    private Letter[] letters; // Масив літер, що складають слово

    /**
     * Конструктор для створення слова.
     *
     * @param value рядок, що представляє слово.
     */
    public Word(String value) {
        letters = new Letter[value.length()];
        for (int i = 0; i < value.length(); i++) {
            letters[i] = new Letter(value.charAt(i));
        }
    }

    /**
     * Метод для отримання слова у вигляді рядка.
     *
     * @return рядок, що представляє слово.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Letter letter : letters) {
            result.append(letter.getValue());
        }
        return result.toString();
    }
}

/**
 * Клас Sentence представляє речення, що складається з масиву слів та розділових знаків.
 */
class Sentence {
    private Word[] words; // Масив слів у реченні

    /**
     * Конструктор для створення речення.
     *
     * @param value рядок, що представляє речення.
     */
    public Sentence(String value) {
        String[] wordStrings = value.trim().split("\\s+");
        words = new Word[wordStrings.length];
        for (int i = 0; i < wordStrings.length; i++) {
            words[i] = new Word(wordStrings[i]);
        }
    }

    /**
     * Метод для зміни місцями першого та останнього слова у реченні.
     */
    public void swapFirstAndLastWords() {
        if (words.length > 1) {
            Word temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
    }

    /**
     * Метод для отримання речення у вигляді рядка.
     *
     * @return рядок, що представляє речення.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Word word : words) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(word.toString());
        }
        return result.toString();
    }
}

/**
 * Клас Text представляє текст, що складається з масиву речень.
 */
class Text {
    private Sentence[] sentences; // Масив речень у тексті

    /**
     * Конструктор для створення тексту.
     *
     * @param value рядок, що представляє текст.
     */
    public Text(String value) {
        String[] sentenceStrings = value.split("\\.\\s*");
        sentences = new Sentence[sentenceStrings.length];
        for (int i = 0; i < sentenceStrings.length; i++) {
            sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }

    /**
     * Метод для нормалізації пробілів у тексті (заміна табуляцій та зайвих пробілів одним пробілом).
     */
    public void normalizeSpaces() {
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = new Sentence(sentences[i].toString().replaceAll("\\s+", " "));
        }
    }

    /**
     * Метод для зміни місцями першого та останнього слова у кожному реченні тексту.
     */
    public void swapFirstAndLastWords() {
        for (Sentence sentence : sentences) {
            sentence.swapFirstAndLastWords();
        }
    }

    /**
     * Метод для отримання тексту у вигляді рядка.
     *
     * @return рядок, що представляє текст.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Sentence sentence : sentences) {
            if (result.length() > 0) {
                result.append(". ");
            }
            result.append(sentence.toString());
        }
        result.append(".");
        return result.toString();
    }
}
