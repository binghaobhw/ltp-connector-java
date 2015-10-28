package cn.edu.hit.ir.ltp.http
import spock.lang.Specification

class HttpClientImplTest extends Specification {
    def "get"() {
        given:
        def httpClient = new HttpClientImpl()

        when:
        def response = httpClient.get("http://www.baidu.com", null)

        then:
        !response.isEmpty()
    }
}
