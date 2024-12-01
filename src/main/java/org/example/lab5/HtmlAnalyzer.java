package org.example.lab5;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class HtmlAnalyzer implements Serializable {
    private final Map<String, Integer> tagFrequency = new HashMap<>();

    public void analyzeUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        //System.out.println(document);
        for(Element element : document.getAllElements()) {
            String tag = element.tagName();
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
        }
    }

    public Map<String, Integer> getTagsSortedByName() {
        return new TreeMap<>(tagFrequency);
    }

    public List<Map.Entry<String, Integer>> getTagsSortedByFrequency() {
        List<Map.Entry<String, Integer>> sortedByFrequency = new ArrayList<>(tagFrequency.entrySet());
        sortedByFrequency.sort(Map.Entry.comparingByValue());
        return sortedByFrequency;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HtmlAnalyzer analyzer = new HtmlAnalyzer();

        try {
            System.out.println("Введіть URL для аналізу:");
            String url = scanner.nextLine();

            analyzer.analyzeUrl(url);

            System.out.println("\nТеги у лексикографічному порядку:");
            Map<String, Integer> sortedByName = analyzer.getTagsSortedByName();
            sortedByName.forEach((tag, count) -> System.out.println(tag + ": " + count));

            System.out.println("\nТеги у порядку зростання за частотою:");
            List<Map.Entry<String, Integer>> sortedByFrequency = analyzer.getTagsSortedByFrequency();
            sortedByFrequency.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (IOException e) {
            System.err.println("Помилка при завантаженні сторінки: " + e.getMessage());
        }
    }

}
