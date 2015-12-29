package cn.edu.hit.ir.ltp

import spock.lang.Specification

class LtpCloudServiceTest extends Specification {
    def ltpService = new LtpCloudService('http://api.ltp-cloud.com/analysis/',
            'u1Q1k8U6tglHca7ZZJ6qTBaq2k0QYwyXNqyE3kVu')

    def "analyze task all"() {
        when:
        def r = ltpService.analyze('我是中国人', Task.ALL)

        then:
        r.tasks.sent && r.tasks.word && r.tasks.pos && r.tasks.parser && r.tasks.ne
        r.tasks.semparser && r.tasks.srl
    }

    def 'analyze task ws'() {
        when:
        def ltpResult = ltpService.analyze('我是中国人。\n我在哈尔滨。', Task.WS)

        then:
        ltpResult.tasks.sent
        ltpResult.tasks.word
        !ltpResult.tasks.ne
        !ltpResult.tasks.parser
        !ltpResult.tasks.semparser
        !ltpResult.tasks.srl
        ltpResult.paras.size() == 2
    }
}
