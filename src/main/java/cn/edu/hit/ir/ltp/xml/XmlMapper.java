package cn.edu.hit.ir.ltp.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class XmlMapper {
    private final Map<Class<?>, Unmarshaller> unmarshallerMap = new HashMap<>();
    private final Map<Class<?>, Marshaller> marshallerMap = new HashMap<>();

    public <T> T unmarshal(Class<T> type, String xml) throws Exception {
        Unmarshaller unmarshaller;
        if (unmarshallerMap.containsKey(type)) {
            unmarshaller = unmarshallerMap.get(type);
        } else {
            unmarshaller = JAXBContext.newInstance(type).createUnmarshaller();
            unmarshallerMap.put(type, unmarshaller);
        }
        JAXBElement<T> root = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), type);
        return root.getValue();
    }

    public String marshal(Object object) throws Exception {
        Marshaller marshaller;
        Class type = object.getClass();
        if (marshallerMap.containsKey(type)) {
            marshaller = marshallerMap.get(type);
        } else {
            marshaller = JAXBContext.newInstance(type).createMarshaller();
            marshallerMap.put(type, marshaller);
        }
        final StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }
}
