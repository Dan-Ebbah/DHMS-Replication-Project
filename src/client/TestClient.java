package client;

import frontend.FrontEndInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class TestClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://192.168.43.7:8080/frontend?wsdl");
            QName qName = new QName("http://frontend/", "FrontEndImplService");
            QName qName2 = new QName("http://frontend/", "FrontEndImplPort");
            Service service = Service.create(url, qName);
            FrontEndInterface port = service.getPort(qName2, FrontEndInterface.class);
            String s = port.addAppointment("", "", "", 1);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
