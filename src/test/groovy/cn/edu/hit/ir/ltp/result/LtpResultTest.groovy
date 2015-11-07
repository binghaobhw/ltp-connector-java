package cn.edu.hit.ir.ltp.result

import spock.lang.Specification

import java.util.function.Predicate


class LtpResultTest extends Specification {
    LtpResult ltpResult

    def setup() {
        ltpResult = new LtpResult([new Para(0, [
                new Sent(0, '我们都是中国人。', [
                    new Word(0, '我们', 'r', 'O', 2, 'SBV', []),
                    new Word(1, '都', 'd', 'O', 2, 'ADV', []),
                    new Word(2, '是', 'v', 'O', -1, 'HED', [
                            new Arg(0, 'A0', 0, 0),
                            new Arg(1, 'AM-ADV', 1, 1)
                    ]),
                    new Word(3, '中国', 'ns', 'S-Ns', 4, 'ATT', []),
                    new Word(4, '人', 'n', 'O', 2, 'VOB', []),
                    new Word(5, '。', 'wp', 'O', 2, 'WP', [])
                ]),
                new Sent(1, '你好！', [
                        new Word(0, '你好', 'i', 'O', -1, 'HED', []),
                        new Word(1, '！', 'wp', 'O', 0, 'WP', [])
                ]),
        ])])
    }

    def 'get punctuations'() {
        expect:
        ltpResult.filter({it.pos == 'wp'} as Predicate).size() == 2
    }

    def "get words"() {
        expect:
        ltpResult.getWords().size() == 8
    }

    def "get non-stop words"() {
        given:
        ltpResult.paras[0].sents[0].words[2].stopWord = true

        expect:
        ltpResult.getNonStopWords().size() == 7
    }

    def "get sentences"() {
        expect:
        ltpResult.getSents().size() == 2
    }
}
