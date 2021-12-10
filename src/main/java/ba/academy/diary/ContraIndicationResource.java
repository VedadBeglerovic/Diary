package ba.academy.diary;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.dto.TherapyDto;
import ba.academy.diary.services.ContraIndicationService;
import ba.academy.diary.services.TherapyService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static java.lang.Boolean.FALSE;

@Path("/contraindication")
public class ContraIndicationResource {

  @Inject
  ContraIndicationService contraIndicationService;

  @GET
  public Response getContrandications()
  {
    List<ContraIndicationDto> contraIndicationDtos = contraIndicationService.getContraIndicatons();
    if(contraIndicationDtos == null || contraIndicationDtos.isEmpty()) {
      return Response.noContent().build();
    }
    return  Response.ok(contraIndicationDtos).build();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createTherapy(ContraIndicationDto contraIndicationDto, @Context UriInfo uriInfo)
  {
    ContraIndicationDto storedContraIndication = contraIndicationService.addContraIndications(contraIndicationDto);


    if(storedContraIndication != null) {
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      uriBuilder.path(Integer.toString(storedContraIndication.getId()));
      return Response.created(uriBuilder.build()).entity(storedContraIndication).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateContraIndication(ContraIndicationDto contraIndicationDto, @PathParam("id") int id) {
    ContraIndicationDto contraIndication = contraIndicationService.updateContraIndicationInput(id, contraIndicationDto);
    if (contraIndication != null) {
      return Response.status(Response.Status.OK).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @GET
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)

  public Response getContraIndication(@PathParam("id") int id) {
    ContraIndicationDto contraIndication = contraIndicationService.getContraIndicationById(id);
    if (contraIndication != null) {
      return Response.ok(contraIndication).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteContraIndicationById(@PathParam("id") int id) {
    boolean deleted= contraIndicationService.deleteContraIndicationById(id);
    if (deleted==FALSE) {
      return Response.noContent().build();
    }
    return Response.ok().build();
  }
}
