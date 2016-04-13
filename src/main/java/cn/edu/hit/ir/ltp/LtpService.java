package cn.edu.hit.ir.ltp;


import cn.edu.hit.ir.ltp.result.LtpResult;

import java.util.List;

public interface LtpService {
    LtpResult analyze(String text, Task... tasks) throws Exception;
    List<LtpResult> analyze(List<String> textList, Task... tasks) throws Exception;
    LtpResult furtherAnalyze(LtpResult source, Task... tasks) throws Exception;
}
