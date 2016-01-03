package org.prototype.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.prototype.model.Product;
import org.prototype.model.Order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vadim on 28.11.2015.
 */
public class RestClient {

    private static Logger log = Logger.getLogger(RestClient.class);


    public static void main(String[] args) {

    try {


        Client client = Client.create();

        WebResource webResource = client
                .resource("http://localhost:8080/makePreOrder");

        Order order = new Order( 1L );
        Product item = new Product( 1L, "Coke", new BigDecimal( 30 ) );
        order.setId( "E_123" );
        order.setOrderTime(new Date());
        order.setPickupTime(new Date());
        order.addProduct( item, 2 );

        ObjectMapper toJSONMapper = new ObjectMapper();
        String data = toJSONMapper.writeValueAsString(order);
        System.out.println("JSON: " + data);

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, data);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);

    } catch (Exception e) {

        e.printStackTrace();

    }




    }


}
