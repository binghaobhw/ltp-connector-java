package cn.edu.hit.ir.ltp.xml

import cn.edu.hit.ir.ltp.result.LtpResult
import spock.lang.Specification


class LtmlMapperImplTest extends Specification {
    def "Unmarshal"() {
        given:
        def ltmlMapper = new LtmlMapperImpl()
        def ltml = '''<?xml version="1.0" encoding="utf-8" ?>
<xml4nlp>
    <note sent="y" word="y" pos="y" ne="y" parser="y" wsd="y" srl="y" />
    <doc>
        <para id="0">
            <sent id="0" cont="我们都是中国人">
                <word id="0" cont="我们" pos="r" ne="O" parent="2" relate="SBV" />
                <word id="1" cont="都" pos="d" ne="O" parent="2" relate="ADV" />
                <word id="2" cont="是"  pos="v" ne="O" parent="-1" relate="HED">
                    <arg id="0" type="A0" beg="0" end="0" />
                    <arg id="1" type="AM-ADV" beg="1" end="1" />
                </word>
                <word id="3" cont="中国" pos="ns" ne="S-Ns" parent="4" relate="ATT" />
                <word id="4" cont="人" pos="n" ne="O" parent="2" relate="VOB" />
            </sent>
        </para>
    </doc>
</xml4nlp>'''

        when:
        def result = ltmlMapper.unmarshal(ltml)

        then:
        result instanceof LtpResult
        result.getWords().size() == 5
    }
}
