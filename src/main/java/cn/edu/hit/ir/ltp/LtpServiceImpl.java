package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.http.HttpClient;
import cn.edu.hit.ir.ltp.http.HttpClientImpl;
import cn.edu.hit.ir.ltp.result.LtpResult;
import cn.edu.hit.ir.ltp.xml.LtmlMapper;
import cn.edu.hit.ir.ltp.xml.LtmlMapperImpl;

import java.util.HashMap;
import java.util.Map;

public class LtpServiceImpl extends BaseLtpService {
    protected final String url;
    protected final HttpClient httpClient;
    protected final LtmlMapper ltmlMapper;

    public LtpServiceImpl(String url) {
        this(url, new HttpClientImpl(), new LtmlMapperImpl());
    }

    public LtpServiceImpl(String url, HttpClient httpClient, LtmlMapper ltmlMapper) {
        this.url = url;
        this.httpClient = httpClient;
        this.ltmlMapper = ltmlMapper;
    }

    @Override
    public LtpResult analyze(String text, Task... tasks) throws Exception {
        checkTasks(tasks);
        String ltml = invoke(text, tasks[0], false);
        for (int i = 1; i < tasks.length; i++) {
            ltml = invoke(ltml, tasks[i], true);
        }
        return ltmlMapper.unmarshal(ltml);
    }

    @Override
    public LtpResult furtherAnalyze(LtpResult source, Task... tasks) throws Exception {
        checkTasks(tasks);
        String ltml = ltmlMapper.marshal(source);
        for (Task task : tasks) {
            ltml = invoke(ltml, task, true);
        }
        return ltmlMapper.unmarshal(ltml);
    }

    private String invoke(String text, Task task, boolean xmlInput) throws Exception {
        Map<String, Object> fields = fields(text, task, xmlInput);
        return httpClient.post(url, fields);
    }

    protected Map<String, Object> fields(String text, Task task, boolean xmlInput) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("s", text);
        fields.put("t", task);
        fields.put("f", "xml");
        fields.put("x", xmlInput ? "y" : "n");
        return fields;
    }

    private static void checkTasks(Task... tasks) {
        if (tasks == null || tasks.length == 0) {
            throw new IllegalArgumentException("tasks must be non-null or emtpy");
        }
        for (Task task : tasks) {
            if (task == null) {
                throw new IllegalArgumentException("task cannot be null");
            }
        }
    }
}
