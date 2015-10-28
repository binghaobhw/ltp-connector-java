package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sent")
public class Sent {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String cont;
    @XmlElement(name = "word")
    private List<Word> words;

    private Sent() {
    }

    public Sent(int id, String cont, List<Word> words) {
        this.id = id;
        this.cont = cont;
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public String getCont() {
        return cont;
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return "Sent{id=" + id + ", cont='" + cont + "', words=" + words + '}';
    }
}
