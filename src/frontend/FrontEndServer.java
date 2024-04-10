package frontend;

import javax.xml.ws.Endpoint;

public class FrontEndServer {

    public static void main(String[] args) {
        Endpoint frontEndServiceEndpoint = Endpoint.publish("http://localhost:8080/frontend", new FrontEndImpl());

        System.out.println("FrontEnd service is published: " + frontEndServiceEndpoint.isPublished());
    }
}
