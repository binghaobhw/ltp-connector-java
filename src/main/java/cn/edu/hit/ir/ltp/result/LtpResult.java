package cn.edu.hit.ir.ltp.result;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@XmlRootElement(name = "xml4nlp")
@XmlAccessorType(XmlAccessType.FIELD)
public class LtpResult implements Operations {
    @XmlElement(name = "note")
    public Tasks tasks;
    @XmlPath("doc/para")
    public List<Para> paras;

    private LtpResult() {}

    public LtpResult(List<Para> paras) {
        this.paras = paras;
        tasks = new Tasks();
    }

    public List<Word> getWords() {
        List<Word> words = new LinkedList<>();
        paras.forEach(p -> p.sents.forEach(s -> words.addAll(s.words)));
        return words;
    }

    @Override
    public List<Word> getNonStopWords() {
        return filter(word -> !word.stopWord);
    }

    @Override
    public List<Word> filter(Predicate<Word> predicate) {
        List<Word> result = new LinkedList<>();
        paras.forEach(p -> p.sents.forEach(s -> s.words.stream().filter(predicate).forEach(result::add)));
        return result;
    }

    @Override
    public void forEachWord(Consumer<Word> action) {
        paras.forEach(para -> para.forEachWord(action));
    }

    public List<Sent> getSents() {
        List<Sent> sents = new ArrayList<>();
        paras.forEach(p -> sents.addAll(p.sents));
        return sents;
    }

    public String getCont() {
        StringBuilder builder = new StringBuilder();
        paras.forEach(para -> builder.append(para.getCont()));
        return builder.toString();
    }

    @Override
    public String getNonStopWordCont() {
        StringBuilder builder = new StringBuilder();
        paras.forEach(para -> builder.append(para.getNonStopWordCont()));
        return builder.toString();
    }

    @Override
    public List<String> getNamedEntities() {
        List<String> nes = new ArrayList<>();
        paras.forEach(para -> nes.addAll(para.getNamedEntities()));
        return nes;
    }

    @Override
    public String toString() {
        return "LtpResult{" +
                "tasks=" + tasks +
                ", paras=" + paras +
                '}';
    }
}
