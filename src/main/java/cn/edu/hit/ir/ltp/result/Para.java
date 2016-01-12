package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Para implements Operations {
    @XmlAttribute
    public int id;
    @XmlElement(name = "sent")
    public List<Sent> sents;

    private Para() {}

    public Para(int id, List<Sent> sents) {
        this.id = id;
        this.sents = sents;
    }

    @Override
    public List<Word> filter(Predicate<Word> predicate) {
        List<Word> result = new ArrayList<>();
        sents.forEach(sent -> sent.words.stream().filter(predicate).forEach(result::add));
        return result;
    }

    @Override
    public void forEachWord(Consumer<Word> action) {
        sents.forEach(sent -> sent.forEachWord(action));
    }

    @Override
    public List<Word> getNonStopWords() {
        return filter(word -> !word.stopWord);
    }

    @Override
    public String getCont() {
        StringBuilder builder = new StringBuilder();
        sents.forEach(sent -> builder.append(sent.getCont()));
        return builder.toString();
    }

    @Override
    public String getNonStopWordCont() {
        StringBuilder builder = new StringBuilder();
        sents.forEach(sent -> builder.append(sent.getNonStopWordCont()));
        return builder.toString();
    }

    @Override
    public List<String> getNamedEntities() {
        List<String> nes = new ArrayList<>();
        sents.forEach(sent -> nes.addAll(sent.getNamedEntities()));
        return nes;
    }

    @Override
    public String toString() {
        return "Para{" +
                "id=" + id +
                ", sents=" + sents +
                '}';
    }
}
