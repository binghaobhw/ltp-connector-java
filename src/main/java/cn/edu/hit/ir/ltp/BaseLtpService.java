package cn.edu.hit.ir.ltp;

import cn.edu.hit.ir.ltp.result.LtpResult;
import cn.edu.hit.ir.ltp.result.Para;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseLtpService implements LtpService {

    @Override
    public List<LtpResult> analyze(List<String> textList, Task... tasks) throws Exception {
        String text = joinParagraphs(removeLineBreaker(textList));
        LtpResult all = analyze(text, tasks);
        return divideIntoLtpResult(all);
    }

    static List<String> removeLineBreaker(List<String> textList) {
        return textList.stream().map(s -> s.replace('\r', ' ').replace('\n', ' ')).collect(Collectors.toList());
    }

    static String joinParagraphs(List<String> paragraphs) {
        return paragraphs.stream().collect(Collectors.joining("\n"));
    }

    static List<LtpResult> divideIntoLtpResult(LtpResult all) {
        return all.paras.stream().map(p -> {
            List<Para> paras = new ArrayList<>(1);
            paras.add(new Para(0, p.sents));
            return new LtpResult(paras, all.tasks);
        }).collect(Collectors.toList());
    }
}
