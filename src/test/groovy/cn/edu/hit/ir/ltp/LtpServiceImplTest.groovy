package cn.edu.hit.ir.ltp
import cn.edu.hit.ir.ltp.http.HttpClientImpl
import cn.edu.hit.ir.ltp.xml.LtmlMapperImpl
import spock.lang.Specification

class LtpServiceImplTest extends Specification {
    LtpService ltpService;
    def setup() {
        ltpService = new LtpServiceImpl("http://127.0.0.1:12345/ltp", new HttpClientImpl(), new LtmlMapperImpl())
    }

    def "analyze"() {
        expect:
        ltpService.analyze('我是中国人').words.size() == 4
    }

    def 'dp with pos'() {
        given:
        def pos = ltpService.analyze('我爱北京天安门！', Task.POS)

        when:
        def dp = ltpService.analyze(pos, Task.DP)

        then:
        dp.tasks.sent && dp.tasks.word && dp.tasks.pos && dp.tasks.parser
        !dp.tasks.ne && !dp.tasks.semparser && !dp.tasks.srl
    }
}
