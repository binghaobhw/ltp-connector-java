package cn.edu.hit.ir.ltp

import cn.edu.hit.ir.ltp.http.HttpClientImpl
import cn.edu.hit.ir.ltp.xml.LtmlMapperImpl
import spock.lang.Specification

class LtpCloudServiceTest extends Specification {
    LtpService ltpService
    def setup() {
        ltpService = new LtpCloudService(
                'http://api.ltp-cloud.com/analysis/',
                'u1Q1k8U6tglHca7ZZJ6qTBaq2k0QYwyXNqyE3kVu',
                new HttpClientImpl(),
                new LtmlMapperImpl()
        )
    }

    def "analyze task all"() {
        expect:
        ltpService.analyze('我是中国人').words.size() == 4
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
