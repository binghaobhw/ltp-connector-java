package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.http.HttpClient;
import cn.edu.hit.ir.ltp.result.LtpResult;
import cn.edu.hit.ir.ltp.xml.LtmlMapper;

import java.util.HashMap;
import java.util.Map;

public class LtpServiceImpl implements LtpService {
    private final String url;
    private final HttpClient httpClient;
    private final LtmlMapper ltmlMapper;

    public LtpServiceImpl(String url, HttpClient httpClient, LtmlMapper ltmlMapper) {
        this.url = url;
        this.httpClient = httpClient;
        this.ltmlMapper = ltmlMapper;
    }

    @Override
    public LtpResult analyze(String text) throws Exception {
        Map<String, Object> fields = new HashMap<>();
        fields.put("x", "n");
        fields.put("t", "all");
        fields.put("s", text);
        String response = httpClient.post(url, fields);
        return ltmlMapper.unmarshal(response);
    }
}
