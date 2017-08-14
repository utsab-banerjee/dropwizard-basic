package com.utsab.resources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utsab.DAOs.MerchantDAO;
import com.utsab.Models.MerchantAttributeDetail;
import com.utsab.Models.MerchantDetail;
import com.utsab.repositories.Merchant;
import com.utsab.repositories.MerchantAttribute;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
@Slf4j
@Path("/dropwizard/basic")
public class BasicController {

    private MerchantDAO merchantDAO;

    public BasicController(MerchantDAO merchantDAO) {
        this.merchantDAO = merchantDAO;
    }

    // TODO: POST: http://localhost:8080/dropwizard/basic/create
    // {
    //     "merchantName": "Paytm",
    //         "merchantCode": "PTM",
    //         "userName": "paytm",
    //         "password": "jkhkjhkl",
    //         "merchantAttributeDetails": [
    //     {
    //         "attributeName": "GSTN",
    //             "attributeValue": "GSTN1"
    //     },
    //     {
    //         "attributeName": "CGSTN",
    //             "attributeValue": "CGSTN1"
    //     }
	// 	]
    // }
    @POST
    @Path("/create")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        try {
            MerchantDetail merchantDetail = new ObjectMapper().readValue(body, MerchantDetail.class);
            List<MerchantAttribute> merchantAttributes = new ArrayList<>();
            Long id = merchantDAO.createMerchant(new Merchant(merchantDetail.getMerchantName(), merchantDetail.getMerchantCode(), merchantDetail.getUserName(), merchantDetail.getPassword(), true, merchantAttributes));
            for (MerchantAttributeDetail merchantAttributeDetail : merchantDetail.getMerchantAttributeDetails()) {
                merchantAttributes.add(new MerchantAttribute(id, merchantAttributeDetail.getAttributeName(), merchantAttributeDetail.getAttributeValue(), true));
            }
        } catch (JsonParseException | JsonMappingException e) {
            log.error("Error: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(body).build();
        } catch (IOException e) {
            log.error("Error: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(body).build();
        }
        return Response.status(Response.Status.CREATED).entity(body).build();
    }

    // TODO: GET: http://localhost:8080/dropwizard/basic/find/myntra
    @GET
    @Path("/find/{username}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("username") String userName) throws IOException {
        Merchant merchant = merchantDAO.findByUserName(userName);
        String valueAsString = new ObjectMapper().writeValueAsString(merchant);
        log.info(valueAsString);
        return Response.status(Response.Status.FOUND).entity(valueAsString).build();
    }

    // TODO: http://localhost:8080/dropwizard/basic/find?code=MYN
    @GET
    @Path("/find")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("code") String merchantCode) throws IOException {
        Merchant merchant = merchantDAO.findByMerchantCode(merchantCode);
        String valueAsString = new ObjectMapper().writeValueAsString(merchant);
        log.info(valueAsString);
        return Response.status(Response.Status.FOUND).entity(valueAsString).build();
    }
}
