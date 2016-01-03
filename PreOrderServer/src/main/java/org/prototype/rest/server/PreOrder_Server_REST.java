package org.prototype.rest.server;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.prototype.model.Order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 28.11.2015.
 */
@Path("/")
public class PreOrder_Server_REST {

    private Logger log = Logger.getLogger(PreOrder_Server_REST.class);

    private List< Order > orders;



    @POST
    @Path("/makePreOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processPreOrderData(Order order) {


        if(isOrdersEmpty()) {
            orders = new ArrayList<>();
        }


        if(isIncommingOrderEmpty(order)) {
            return Response.status(500).entity("The -=Order=- sent to REST service is null").build();
        }



        Order newOrder = order;
        System.out.println("Server internals: " + newOrder.toString());

        String writeFileTo = "./order.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(writeFileTo), order);
        } catch (IOException e) { e.printStackTrace(); }


        return Response.status(200).entity("JSON :" + newOrder.toString() + " stored in " + writeFileTo).build();
    }



    private boolean isIncommingOrderEmpty(Order order) {

        if(order == null) {
            return true;
        }

        return false;
    }



    private boolean isOrdersEmpty() {
        if(this.orders == null) {
            return true;
        }
        return false;
    }



}
