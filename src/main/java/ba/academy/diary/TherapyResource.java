package ba.academy.diary;


import ba.academy.diary.dto.DiaryDto;
import ba.academy.diary.dto.TherapyDto;
import ba.academy.diary.services.TherapyService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/therapy")
public class TherapyResource {

  @Inject TherapyService therapyService;
  @GET
  public Response getTherapies()
  {
    List<TherapyDto> therapyDtos = therapyService.getTherapies();
    if(therapyDtos == null || therapyDtos.isEmpty()) {
      return Response.noContent().build();
    }
    return  Response.ok(therapyDtos).build();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createTherapy(TherapyDto therapyDto, @Context UriInfo uriInfo)
  {
    TherapyDto storedTherapy = therapyService.addTherapy(therapyDto);


    if(storedTherapy != null) {
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      uriBuilder.path(Integer.toString(storedTherapy.getId()));
      return Response.created(uriBuilder.build()).entity(storedTherapy).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }
}
