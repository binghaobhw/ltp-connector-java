package cn.edu.hit.ir.ltp.xml;

import cn.edu.hit.ir.ltp.result.LtpResult;

public class LtmlMapperImpl extends XmlMapper implements LtmlMapper {

    private boolean isLtml(String response) {
        return response.contains("<xml4nlp>") && response.contains("<doc>");
    }

    private String extractDocPart(String ltml) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                ltml.substring(ltml.indexOf("<doc>"), ltml.lastIndexOf("</xml4nlp>"));
    }

    @Override
    public LtpResult unmarshal(String xml) throws Exception {
        if (!isLtml(xml)) {
            throw new Exception("Invalid LTML format");
        }
        String doc = extractDocPart(xml);
        return unmarshal(LtpResult.class, doc);
    }
}
