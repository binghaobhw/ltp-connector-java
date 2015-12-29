package cn.edu.hit.ir.ltp

import spock.lang.Specification

class LtpServiceImplTest extends Specification {
    def ltpService = new LtpServiceImpl("http://127.0.0.1:12345/ltp")

    def 'throw IllegalArgumentException when tasks is null'() {
        when:
        ltpService.analyze('a', null)

        then:
        thrown(IllegalArgumentException)
    }

    def 'throw IllegalArgumentException when tasks is empty'() {
        when:
        ltpService.analyze('a', [] as Task[])

        then:
        thrown(IllegalArgumentException)
    }

    def 'throw IllegalArgumentException when tasks contain null'() {
        when:
        ltpService.analyze('a', [null] as Task[])

        then:
        thrown(IllegalArgumentException)
    }

    def 'analyze NER and DP'() {
        when:
        def r = ltpService.analyze('我是中国人', Task.NE, Task.DP)

        then:
        r.tasks.sent && r.tasks.word && r.tasks.pos && r.tasks.parser && r.tasks.ne
        !r.tasks.semparser && !r.tasks.srl
    }

    def 'dp with pos'() {
        given:
        def pos = ltpService.analyze('我爱北京天安门！', Task.POS)

        when:
        def dp = ltpService.furtherAnalyze(pos, Task.DP)

        then:
        dp.tasks.sent && dp.tasks.word && dp.tasks.pos && dp.tasks.parser
        !dp.tasks.ne && !dp.tasks.semparser && !dp.tasks.srl
    }
}
