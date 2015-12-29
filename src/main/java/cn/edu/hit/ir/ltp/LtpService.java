package cn.edu.hit.ir.ltp;


import cn.edu.hit.ir.ltp.result.LtpResult;

public interface LtpService {
    LtpResult analyze(String text, Task... tasks) throws Exception;
    LtpResult furtherAnalyze(LtpResult source, Task... tasks) throws Exception;
}
