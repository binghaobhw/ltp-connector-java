package cn.edu.hit.ir.ltp.result

import spock.lang.Specification


class ParaTest extends Specification {
    def "named entities for '毛泽东生日 哈尔滨'"() {
        given:
        def words1 = [new Word(0, '毛泽东'), new Word(1, '生日')]
        words1[0].ne = 'S-Nh'
        words1[1].ne = 'O'
        def words2 = [new Word(0, '哈尔滨')]
        words2[0].ne = 'B-Ni'
        def para = new Para(0, [new Sent(0, '毛泽东生日', words1), new Sent(1, '哈尔滨', words2)])

        when:
        def nes = para.getNamedEntities()

        then:
        nes == ['毛泽东', '哈尔滨']
    }
}
