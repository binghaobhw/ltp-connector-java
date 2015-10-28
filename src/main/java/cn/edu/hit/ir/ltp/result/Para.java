package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "para")
public class Para {
    @XmlAttribute
    private int id;
    @XmlElement(name = "sent")
    private List<Sent> sents;

    private Para() {
    }

    public Para(int id, List<Sent> sents) {
        this.id = id;
        this.sents = sents;
    }

    public int getId() {
        return id;
    }

    public List<Sent> getSents() {
        return sents;
    }

    @Override
    public String toString() {
        return "Para{id=" + id + ", sents=" + sents + '}';
    }
}
