package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Sent {
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
    public String toString() {
        return "Sent{" +
                "id=" + id +
                ", cont='" + cont + '\'' +
                ", words=" + words +
                '}';
    }
}
