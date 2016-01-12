package cn.edu.hit.ir.ltp.result;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Operations {
    List<Word> filter(Predicate<Word> predicate);
    void forEachWord(Consumer<Word> action);
    List<Word> getNonStopWords();
    String getCont();
    String getNonStopWordCont();
    List<String> getNamedEntities();
}
