package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sent implements Operations {
    @XmlAttribute
    public int id;
    @XmlAttribute
    public String cont;
    @XmlElement(name = "word")
    public List<Word> words;

    private Sent() {}

    public Sent(int id, String cont, List<Word> words) {
        this.id = id;
        this.cont = cont;
        this.words = words;
    }

    @Override
    public List<Word> filter(Predicate<Word> predicate) {
        return words.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void forEachWord(Consumer<Word> action) {
        words.forEach(action);
    }

    @Override
    public List<Word> getNonStopWords() {
        return filter(word -> !word.stopWord);
    }

    @Override
    public String getCont() {
        return cont;
    }

    @Override
    public String getNonStopWordCont() {
        StringBuilder builder = new StringBuilder();
        forEachWord(word -> {
            if (!word.stopWord) {
                builder.append(word.cont);
            }
        });
        return builder.toString();
    }

    @Override
    public List<String> getNamedEntities() {
        List<String> nes = new ArrayList<>();
        StringBuilder ne = new StringBuilder();
        boolean previousB = false;
        for (Word word : words) {
            if (word.ne.startsWith("B") || word.ne.startsWith("I") || word.ne.startsWith("E") || word.ne.startsWith("S")) {
                ne.append(word.cont);
                previousB = word.ne.startsWith("B");
            }
            if (word.ne.startsWith("E") || word.ne.startsWith("S") || previousB) {
                nes.add(ne.toString());
                ne = new StringBuilder();
            }
        }
        return nes;
    }

    @Override
    public String toString() {
        return "Sent{" +
                "id=" + id +
                ", cont='" + cont + '\'' +
                ", words=" + words +
                '}';
    }
}
