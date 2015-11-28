package cn.edu.hit.ir.ltp.xml;

import cn.edu.hit.ir.ltp.result.LtpResult;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class LtmlMapperImpl implements LtmlMapper {
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public LtmlMapperImpl() {
        try {
            JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{LtpResult.class}, null);
            unmarshaller = jaxbContext.createUnmarshaller();
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            throw new RuntimeException("Fail to initialize", e);
        }
    }

    @Override
    public LtpResult unmarshal(String ltml) throws Exception {
        return (LtpResult) unmarshaller.unmarshal(new StringReader(ltml));
    }

    @Override
    public String marshal(LtpResult ltpResult) throws Exception {
        StringWriter writer = new StringWriter();
        marshaller.marshal(ltpResult, writer);
        return writer.toString();
    }
}
