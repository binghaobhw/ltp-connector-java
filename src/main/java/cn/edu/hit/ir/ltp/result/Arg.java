package cn.edu.hit.ir.ltp.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "arg")
public class Arg {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private int beg;
    @XmlAttribute
    private int end;

    private Arg() {
    }

    public Arg(int id, String type, int beg, int end) {
        this.id = id;
        this.type = type;
        this.beg = beg;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getBeg() {
        return beg;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Arg{" + "id=" + id + ", type='" + type + '\'' + ", beg=" + beg + ", end=" + end + '}';
    }
}
