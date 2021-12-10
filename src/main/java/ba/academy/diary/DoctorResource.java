package ba.academy.diary;


import ba.academy.diary.dto.DoctorDto;
import ba.academy.diary.services.DoctorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static java.lang.Boolean.FALSE;

@Path("/doctor")
public class DoctorResource {

    @Inject
    DoctorService doctorService;

    @GET
    public Response getDoctors()
    {
        List<DoctorDto> doctors = doctorService.getDoctors();
        if(doctors == null || doctors.isEmpty()) {
            return Response.noContent().build();
        }
        return  Response.ok(doctors).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createDoctor(DoctorDto doctorDto, @Context UriInfo uriInfo)
    {
        DoctorDto storedDoctor = doctorService.addDoctorInput(doctorDto);


        if(storedDoctor != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(storedDoctor.getId()));
            return Response.created(uriBuilder.build()).entity(storedDoctor).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(DoctorDto doctorDto, @PathParam("id") int id) {
        DoctorDto doctor = doctorService.updateDoctorInput(id, doctorDto);
        if (doctor != null) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response getDoctor(@PathParam("id") int id) {
        DoctorDto doctorDto = doctorService.getCDoctorById(id);
        if (doctorDto != null) {
            return Response.ok(doctorDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDoctorById(@PathParam("id") int id) {
        boolean deleted= doctorService.deleteDoctorById(id);
        if (deleted==FALSE) {
            return Response.noContent().build();
        }
        return Response.ok().build();
    }
}
