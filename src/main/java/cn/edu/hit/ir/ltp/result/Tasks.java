package cn.edu.hit.ir.ltp.result;

import cn.edu.hit.ir.ltp.xml.TaskAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Tasks {
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean sent = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean word = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean pos = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean ne = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean parser = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean semparser = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean wsd = false;
    @XmlAttribute
    @XmlJavaTypeAdapter(TaskAdapter.class)
    public boolean srl = false;

    public Tasks() {}

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
