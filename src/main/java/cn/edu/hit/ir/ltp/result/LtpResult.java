package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "doc")
public class LtpResult {
    @XmlElement(name = "para")
    private List<Para> paras;

    private LtpResult() {}

    public LtpResult(List<Para> paras) {
        this.paras = paras;
    }

    public List<Para> getParas() {
        return paras;
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        paras.forEach(p -> p.getSents().forEach(s -> words.addAll(s.getWords())));
        return words;
    }

    public List<Word> getNonStopWords() {
        List<Word> nonStopWords = new ArrayList<>();
        paras.forEach(p -> p.getSents().forEach(s -> s.getWords().stream().filter(w -> !w.isStopWord()).forEach(nonStopWords::add)));
        return nonStopWords;
    }

    public List<Sent> getSents() {
        List<Sent> sents = new ArrayList<>();
        paras.forEach(p -> sents.addAll(p.getSents()));
        return sents;
    }

    public String getCont() {
        StringBuilder builder = new StringBuilder();
        for (Para para: paras) {
            for (Sent sent: para.getSents()) {
                builder.append(sent.getCont());
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "LtpResult{paras=" + paras + '}';
    }
}
