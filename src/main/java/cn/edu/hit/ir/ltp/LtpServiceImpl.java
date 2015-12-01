package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.http.HttpClient;
import cn.edu.hit.ir.ltp.result.LtpResult;
import cn.edu.hit.ir.ltp.xml.LtmlMapper;

import java.util.HashMap;
import java.util.Map;

public class LtpServiceImpl implements LtpService {
    protected final String url;
    protected final HttpClient httpClient;
    protected final LtmlMapper ltmlMapper;

    public LtpServiceImpl(String url, HttpClient httpClient, LtmlMapper ltmlMapper) {
        this.url = url;
        this.httpClient = httpClient;
        this.ltmlMapper = ltmlMapper;
    }

    @Override
    public LtpResult analyze(String text) throws Exception {
        return invoke(fields(text, Task.ALL, false));
    }

    @Override
    public LtpResult analyze(String text, Task task) throws Exception {
        return invoke(fields(text, task, false));
    }

    @Override
    public LtpResult analyze(LtpResult source, Task task) throws Exception {
        String ltml = ltmlMapper.marshal(source);
        return invoke(fields(ltml, task, true));
    }

    protected Map<String, Object> fields(String text, Task task, boolean xmlInput) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("s", text);
        fields.put("t", task);
        fields.put("x", xmlInput ? "y" : "n");
        return fields;
    }

    private LtpResult invoke(Map<String, Object> fields) throws Exception {
        String ltml = httpClient.post(url, fields);
        return ltmlMapper.unmarshal(ltml);
    }
}
