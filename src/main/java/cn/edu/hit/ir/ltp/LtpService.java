package cn.edu.hit.ir.ltp;


import cn.edu.hit.ir.ltp.result.LtpResult;

public interface LtpService {
    LtpResult analyze(String text) throws Exception;
    LtpResult analyze(String text, Task task) throws Exception;
    LtpResult analyze(LtpResult source, Task task) throws Exception;
}
