package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Word {
    @XmlAttribute
    public int id;
    @XmlAttribute
    public String cont;
    @XmlAttribute
    public String pos;
    @XmlAttribute
    public String ne;
    @XmlAttribute
    public Integer parent;
    @XmlAttribute
    public String relate;
    @XmlAttribute(name = "semparent")
    public Integer semParent;
    @XmlAttribute(name = "semrelate")
    public String semRelate;
    @XmlElement(name = "arg")
    public List<Arg> args;

    @XmlTransient
    public boolean stopWord = false;

    private Word() {}

    public Word(int id, String cont) {
        this(id, cont, null, null, null, null, null, null, null);
    }

    public Word(int id, String cont, String pos) {
        this(id, cont, pos, null, null, null, null, null, null);
    }

    public Word(int id, String cont, String pos, String ne, Integer parent, String relate, Integer semParent, String semRelate, List<Arg> args) {
        this.id = id;
        this.cont = cont;
        this.pos = pos;
        this.ne = ne;
        this.parent = parent;
        this.relate = relate;
        this.semParent = semParent;
        this.semRelate = semRelate;
        this.args = args;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", cont='" + cont + '\'' +
                ", pos='" + pos + '\'' +
                ", ne='" + ne + '\'' +
                ", parent=" + parent +
                ", relate='" + relate + '\'' +
                ", semParent=" + semParent +
                ", semRelate='" + semRelate + '\'' +
                ", args=" + args +
                ", stopWord=" + stopWord +
                '}';
    }
}
