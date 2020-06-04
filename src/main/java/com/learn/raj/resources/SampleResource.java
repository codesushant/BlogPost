package com.learn.raj.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@Path("/sample")
//@Produces(MediaType.TEXT_PLAIN)
public class SampleResource {

    @GET
//    @UnitOfWork
    @Path("/test")
    public String sampleGet(){
        return String.format("Welcome %s", "Stranger");
    }

    @GET
    @Path("/{name}")
    public String sampleGetOptional( @PathParam("name") Optional<String> name){
        return String.format("Welcome %s",name.orElse("Stranger"));
    }
}