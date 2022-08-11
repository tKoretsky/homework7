import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePath = "src/Voina_i_mir_Tom_1.txt";
        int minLenght = 3; //You can choose a minimum word length

        TreeMap<String, Integer> words = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.length() > o2.length() && o1.length() > 2 ||
                        o1.length() == o2.length() && o1.compareTo(o2) > 0) {
                    return 1;
                } else if (o1.length() == o2.length() && o1.compareTo(o2) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        words = getAllWordsFromFile(filePath, words, minLenght);

        System.out.println(words);
        System.out.println("Shortest word: " + words.firstKey());
        System.out.println("Longest word: " + words.lastKey());
        System.out.println(words.size());
    }

    public static TreeMap getAllWordsFromFile(String _filePath, TreeMap<String, Integer> words, int minLenght) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(_filePath, Charset.forName("UTF-8")));
        String _text = "";
        String[] strLines;
        while ((_text = reader.readLine()) != null) {
            strLines = _text.split("[ ,—.?… \\-„>><<;:}{)\\]\\[»«(!'\\w\\s]");
            for (String word : strLines) {
                int count = words.getOrDefault(word, 0);
                if (word.length() >= minLenght) {
                    words.put(word.toLowerCase(), count + 1);
                }
            }
        }
        reader.close();
        return words;
    }
}
