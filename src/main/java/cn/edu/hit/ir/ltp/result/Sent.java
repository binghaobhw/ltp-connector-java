package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
    public String toString() {
        return "Sent{" +
                "id=" + id +
                ", cont='" + cont + '\'' +
                ", words=" + words +
                '}';
    }
}
