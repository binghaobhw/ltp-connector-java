package cn.edu.hit.ir.ltp

import cn.edu.hit.ir.ltp.http.HttpClientImpl
import cn.edu.hit.ir.ltp.xml.LtmlMapperImpl
import spock.lang.Specification


class LtpCloudServiceTest extends Specification {
    LtpService ltpService
    def setup() {
        ltpService = new LtpCloudService(new HttpClientImpl(),
                'http://ltpapi.voicecloud.cn/analysis/', 'u1Q1k8U6tglHca7ZZJ6qTBaq2k0QYwyXNqyE3kVu',
                new LtmlMapperImpl()
        )
    }

    def "analyze"() {
        expect:
        ltpService.analyze('我是中国人').words.size() == 4
    }
}
