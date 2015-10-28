package cn.edu.hit.ir.ltp.http;

import java.util.Map;

public interface HttpClient {
    String get(String url, Map<String, Object> fields) throws Exception;
    String post(String url, Map<String, Object> fields) throws Exception;
}
