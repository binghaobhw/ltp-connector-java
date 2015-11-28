package cn.edu.hit.ir.ltp.xml;

import cn.edu.hit.ir.ltp.result.LtpResult;

public interface LtmlMapper {
    LtpResult unmarshal(String ltml) throws Exception;
    String marshal(LtpResult ltpResult) throws Exception;
}

