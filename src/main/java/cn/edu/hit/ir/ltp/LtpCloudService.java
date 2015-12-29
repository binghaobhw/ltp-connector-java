package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.http.HttpClient;
import cn.edu.hit.ir.ltp.xml.LtmlMapper;

import java.util.HashMap;
import java.util.Map;

public class LtpCloudService extends LtpServiceImpl {
    private final String apiKey;

    public LtpCloudService(String url, String apiKey) {
        super(url);
        this.apiKey = apiKey;
    }

    public LtpCloudService(String url, String apiKey, HttpClient httpClient, LtmlMapper ltmlMapper) {
        super(url, httpClient, ltmlMapper);
        this.apiKey = apiKey;
    }

    @Override
    protected Map<String, Object> fields(String text, Task task, boolean xmlInput) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("api_key", apiKey);
        fields.put("text", text);
        // 'ner' in ltp cloud but 'ne' in ltp server
        fields.put("pattern", (task == Task.NE) ? "ner" : task);
        fields.put("format", "xml");
        fields.put("xml_input", xmlInput ? "y" : "n");
        return fields;
    }
}
