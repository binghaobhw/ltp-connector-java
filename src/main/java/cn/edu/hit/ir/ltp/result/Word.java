package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "word")
public class Word {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String cont;
    @XmlAttribute
    private String pos;
    @XmlAttribute
    private String ne;
    @XmlAttribute
    private int parent;
    @XmlAttribute
    private String relate;
    @XmlElement(name = "arg")
    private List<Arg> args;

    private boolean stopWord = false;

    private Word() {
    }

    public Word(int id, String cont, String pos, String ne, int parent, String relate, List<Arg> args) {
        this.id = id;
        this.cont = cont;
        this.pos = pos;
        this.ne = ne;
        this.parent = parent;
        this.relate = relate;
        this.args = args;
    }

    public int getId() {
        return id;
    }

    public String getCont() {
        return cont;
    }

    public String getPos() {
        return pos;
    }

    public String getNe() {
        return ne;
    }

    public int getParent() {
        return parent;
    }

    public String getRelate() {
        return relate;
    }

    public List<Arg> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "Word{id=" + id + ", cont='" + cont + "', pos='" + pos + "', ne='" + ne + "', parent=" + parent + ", relate='" + relate + "', args=" + args + '}';
    }

    public boolean isStopWord() {
        return stopWord;
    }

    public void setStopWord(boolean stopWord) {
        this.stopWord = stopWord;
    }
}
