package cn.edu.hit.ir.ltp

import cn.edu.hit.ir.ltp.http.HttpClientImpl
import cn.edu.hit.ir.ltp.xml.LtmlMapperImpl
import spock.lang.Specification


class LtpServiceImplTest extends Specification {
    LtpService ltpService;
    def setup() {
        ltpService = new LtpServiceImpl("http://localhost:12345/ltp", new HttpClientImpl(), new LtmlMapperImpl())
    }

    def "analyze"() {
        expect:
        ltpService.analyze('我是中国人').words.size() == 4
    }
}
