package cn.edu.hit.ir.ltp.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TaskAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String v) throws Exception {
        return "y".equals(v);
    }

    @Override
    public String marshal(Boolean v) throws Exception {
        return v ? "y" : "n";
    }
}
