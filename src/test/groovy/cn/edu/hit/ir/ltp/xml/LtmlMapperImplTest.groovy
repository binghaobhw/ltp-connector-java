package cn.edu.hit.ir.ltp.xml

import cn.edu.hit.ir.ltp.result.*
import spock.lang.Specification

class LtmlMapperImplTest extends Specification {
    LtmlMapper ltmlMapper = new LtmlMapperImpl()

    def 'unmarshal pos'() {
        given:
        def ltml = '''<?xml version="1.0" encoding="utf-8" ?>
<xml4nlp>
    <note sent="y" word="y" pos="y" ne="n" parser="n" semparser="n" wsd="n" srl="n" />
    <doc>
        <para id="0">
            <sent id="0" cont="我们都是中国人。">
                <word id="0" cont="我们" pos="r" />
                <word id="1" cont="都" pos="d" />
                <word id="2" cont="是" pos="v" />
                <word id="3" cont="中国" pos="ns" />
                <word id="4" cont="人" pos="n" />
                <word id="5" cont="。" pos="wp" />
            </sent>
        </para>
    </doc>
</xml4nlp>'''

        when:
        LtpResult result = ltmlMapper.unmarshal(ltml)

        then:
        result.tasks.pos
        !result.tasks.ne
        !result.tasks.parser
        !result.tasks.srl
        !result.tasks.semparser
        result.words.get(0).ne == null
        result.words.get(1).parent == null
        result.words.get(2).relate == null
        result.words.get(3).semParent == null
        result.words.get(4).semRelate == null
        result.words.get(5).args == null
    }

    def 'unmarshal all'() {
        given:
        def ltml = '''<?xml version="1.0" encoding="utf-8" ?>
<xml4nlp>
    <note sent="y" word="y" pos="y" ne="y" parser="y" semparser="y" wsd="n" srl="y" />
    <doc>
        <para id="0">
            <sent id="0" cont="我们都是中国人。">
                <word id="0" cont="我们" pos="r" ne="O" parent="2" relate="SBV" semparent="2" semrelate="Exp" />
                <word id="1" cont="都" pos="d" ne="O" parent="2" relate="ADV" semparent="2" semrelate="mRang" />
                <word id="2" cont="是" pos="v" ne="O" parent="-1" relate="HED" semparent="-1" semrelate="Root">
                    <arg id="0" type="A0" beg="0" end="0" />
                    <arg id="1" type="ADV" beg="1" end="1" />
                    <arg id="2" type="A1" beg="3" end="4" />
                </word>
                <word id="3" cont="中国" pos="ns" ne="S-Ns" parent="4" relate="ATT" semparent="4" semrelate="Nmod" />
                <word id="4" cont="人" pos="n" ne="O" parent="2" relate="VOB" semparent="2" semrelate="Clas" />
                <word id="5" cont="。" pos="wp" ne="O" parent="2" relate="WP" semparent="2" semrelate="mPunc" />
            </sent>
        </para>
    </doc>
</xml4nlp>'''

        when:
        LtpResult result = ltmlMapper.unmarshal(ltml)
        List<Word> words = result.getWords()

        then:
        result.getWords().size() == 6
        !result.tasks.wsd
        words.get(0).id == 0
        words.get(1).cont == '都'
        words.get(2).pos == 'v'
        words.get(3).ne == 'S-Ns'
        words.get(4).parent == 2
        words.get(5).relate == 'WP'
        words.get(0).semParent == 2
        words.get(1).semRelate == 'mRang'
        words.get(2).args.get(0).id == 0
        words.get(2).args.get(1).type == 'ADV'
        words.get(2).args.get(2).beg == 3
        words.get(2).args.get(0).end == 0
    }

    def 'marshal ws'() {
        given:
        LtpResult ltpResult = new LtpResult([new Para(0, [
                new Sent(0, '我们都是中国人。', [
                        new Word(0, '我们'),
                        new Word(1, '都'),
                        new Word(2, '是'),
                        new Word(3, '中国'),
                        new Word(4, '人'),
                        new Word(5, '。')])])])
        ltpResult.tasks.pos = false
        ltpResult.tasks.ne = false
        ltpResult.tasks.parser = false
        ltpResult.tasks.srl = false
        ltpResult.tasks.semparser = false

        when:
        String result = ltmlMapper.marshal(ltpResult)

        then:
        result.contains('<note sent="y" word="y" pos="n" ne="n" parser="n" semparser="n" wsd="n" srl="n"/>')
        result.contains('<word id="0" cont="我们"/>')
    }

    def 'marshal all tasks'() {
        given:
        LtpResult ltpResult = new LtpResult([new Para(0, [
                new Sent(0, '我们都是中国人。', [
                        new Word(0, '我们', 'r', 'O', 2, 'SBV', 2, 'Exp', []),
                        new Word(1, '都', 'd', 'O', 2, 'ADV', 2, 'mRang', []),
                        new Word(2, '是', 'v', 'O', -1, 'HED', -1, 'Root', [
                                new Arg(0, 'A0', 0, 0),
                                new Arg(1, 'ADV', 1, 1),
                                new Arg(2, 'A1', 3, 4)
                        ]),
                        new Word(3, '中国', 'ns', 'S-Ns', 4, 'ATT', 4, 'Nmod', []),
                        new Word(4, '人', 'n', 'O', 2, 'VOB', 2, 'Clas', []),
                        new Word(5, '。', 'wp', 'O', 2, 'WP', 2, 'mPunc', [])])])])

        when:
        String result = ltmlMapper.marshal(ltpResult)

        then:
        result.contains('<note sent="y" word="y" pos="y" ne="y" parser="y" semparser="y" wsd="n" srl="y"/>')
        result.contains('<note sent="y" word="y" pos="y" ne="y" parser="y" semparser="y" wsd="n" srl="y"/>')
        result.contains('<word id="1" cont="都" pos="d" ne="O" parent="2" relate="ADV" semparent="2" semrelate="mRang"/>')
        result.contains('<arg id="0" type="A0" beg="0" end="0"/>')
    }
}
