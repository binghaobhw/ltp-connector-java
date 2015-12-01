package cn.edu.hit.ir.ltp.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpClientImpl implements HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);
//    static  {
//        Unirest.setTimeouts(3000L, 10000L);
//    }

    @Override
    public String get(String url, Map<String, Object> fields) throws Exception {
        logger.debug("url='{}', fields={}", url, fields);
        HttpResponse<String> response = Unirest.get(url).queryString(fields).asString();
        return handleResponse(response);
    }

    @Override
    public String post(String url, Map<String, Object> fields) throws Exception {
        logger.debug("url='{}', fields={}", url, fields);
        HttpResponse<String> response = Unirest.post(url).fields(fields).asString();
        return handleResponse(response);
    }

    String handleResponse(HttpResponse<String> response) throws Exception {
        if (response.getStatus() == 200) {
            return response.getBody();
        }
        throw new Exception("Request failed: " + response.getStatus() + " " + response.getStatusText());
    }
}
