package cn.edu.hit.ir.ltp

import cn.edu.hit.ir.ltp.result.LtpResult
import cn.edu.hit.ir.ltp.result.Para
import cn.edu.hit.ir.ltp.result.Sent
import cn.edu.hit.ir.ltp.result.Word
import spock.lang.Specification


class BaseLtpServiceTest extends Specification {
    def "test removeLineBreaker"() {
        given:
        def textList = [
                '''line 1

aaa\n\nb
''',
                'ccc'
        ]

        when:
        def result = BaseLtpService.removeLineBreaker(textList)

        then:
        result.size() == 2
        result[0] == 'line 1  aaa  b '
        result[1] == 'ccc'
    }

    def "test joinParagraphs"() {
        given:
        def paragraphs = ['a', 'b', 'c']

        expect:
        BaseLtpService.joinParagraphs(paragraphs) == 'a\nb\nc'
    }

    def 'test divideIntoLtpResult'() {
        given:
        def all = new LtpResult([
                new Para(0, [new Sent(0, 'a', [new Word(0, 'a')])]),
                new Para(1, [new Sent(0, 'b', [new Word(0, 'b')])])
        ])

        when:
        def result = BaseLtpService.divideIntoLtpResult(all)

        then:
        result.size() == 2
        result[0].paras.size() == 1
        result[1].paras.size() == 1
        result[0].paras[0].sents.size() == 1
        result[0].paras[0].sents[0].cont == 'a'
        result[1].paras[0].sents.size() == 1
        result[1].paras[0].sents[0].cont == 'b'
    }
}
