package cn.edu.hit.ir.ltp.result;

import cn.edu.hit.ir.ltp.xml.TaskAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Tasks {
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean sent;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean word;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean pos;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean ne;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean parser;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean semparser;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean wsd;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean srl;

    public Tasks() {
        sent = true;
        word = true;
        pos = true;
        ne = true;
        parser = true;
        semparser = true;
        wsd = false;
        srl = true;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "sent=" + sent +
                ", word=" + word +
                ", pos=" + pos +
                ", ne=" + ne +
                ", parser=" + parser +
                ", semparser=" + semparser +
                ", wsd=" + wsd +
                ", srl=" + srl +
                '}';
    }
}
