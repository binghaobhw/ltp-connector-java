package cn.edu.hit.ir.ltp;


import cn.edu.hit.ir.ltp.result.LtpResult;

public interface LtpService {
    LtpResult analyze(String text) throws Exception;
}
