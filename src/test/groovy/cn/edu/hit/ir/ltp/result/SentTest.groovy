package cn.edu.hit.ir.ltp.result

import spock.lang.Specification


class SentTest extends Specification {
    def "named entities for '毛泽东生日'"() {
        given:
        def words = [new Word(0, '毛泽东'), new Word(1, '生日')]
        words[0].ne = 'S-Nh'
        words[1].ne = 'O'
        def sent = new Sent(0, '毛泽东生日', words)

        when:
        def nes = sent.getNamedEntities()

        then:
        nes == ['毛泽东']
    }

    def "named entities for '哈尔滨'"() {
        given:
        def words = [new Word(0, '哈尔滨')]
        words[0].ne = 'B-Ni'
        def sent = new Sent(0, '哈尔滨', words)

        when:
        def nes = sent.getNamedEntities()

        then:
        nes == ['哈尔滨']
    }
    def "named entities for '哈尔滨工业大学'"() {
        given:
        def words = [
                new Word(0, '哈尔滨'),
                new Word(1, '工业'),
                new Word(2, '大学'),
        ]
        words[0].ne = 'B-Ni'
        words[1].ne = 'I-Ni'
        words[2].ne = 'E-Ni'
        def sent = new Sent(0, '哈尔滨工业大学', words)

        when:
        def nes = sent.getNamedEntities()

        then:
        nes == ['哈尔滨工业大学']
    }
}
