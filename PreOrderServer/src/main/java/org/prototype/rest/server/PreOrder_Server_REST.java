package org.prototype.rest.server;

import org.apache.log4j.Logger;
import org.prototype.model.Order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vadim on 28.11.2015.
 */
@Path("/")
public class PreOrder_Server_REST {

    private Logger log = Logger.getLogger(PreOrder_Server_REST.class);



    @POST
    @Path("/makePreOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processPreOrderData(Order order) {

        Order newOrder = order;
        System.out.println(newOrder.toString());

        if(newOrder.getOrderID().equals("E_123"))
            return Response.status(200).entity(newOrder.toString()).build();
        else
            return Response.status(422).entity(newOrder.toString()).build();

    }




}
