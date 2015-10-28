package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.http.HttpClient;
import cn.edu.hit.ir.ltp.result.LtpResult;
import cn.edu.hit.ir.ltp.xml.LtmlMapper;

import java.util.HashMap;
import java.util.Map;

public class LtpCloudService implements LtpService {
    private final String url;
    private final String apiKey;
    private final HttpClient httpClient;
    private final LtmlMapper ltmlMapper;

    public LtpCloudService(HttpClient httpClient, String url, String apiKey, LtmlMapper ltmlMapper) {
        this.httpClient = httpClient;
        this.url = url;
        this.apiKey = apiKey;
        this.ltmlMapper = ltmlMapper;
    }

    @Override
    public LtpResult analyze(String text) throws Exception {
        Map<String, Object> fields = new HashMap<>();
        fields.put("api_key", this.apiKey);
        fields.put("pattern", "all");
        fields.put("format", "xml");
        fields.put("text", text);
        String response = httpClient.post(url, fields);
        return ltmlMapper.unmarshal(response);
    }
}
