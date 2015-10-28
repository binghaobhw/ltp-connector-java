package cn.edu.hit.ir.ltp.xml;

import cn.edu.hit.ir.ltp.result.LtpResult;

public interface LtmlMapper {
    LtpResult unmarshal(String xml) throws Exception;
}

