package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Para {
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
    public String toString() {
        return "Para{" +
                "id=" + id +
                ", sents=" + sents +
                '}';
    }
}
