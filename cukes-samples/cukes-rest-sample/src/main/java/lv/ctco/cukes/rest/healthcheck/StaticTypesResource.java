package lv.ctco.cukes.rest.healthcheck;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("SameReturnValue")
@Path(StaticTypesResource.API)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaticTypesResource {

    protected static final String API = "/staticTypes";

    @GET
    public Response staticTypes() {
        return Response.ok("{" +
            "  \"prop\": [" +
            "    {" +
            "      \"int\": 1," +
            "      \"float\": 26.505515," +
            "      \"long\": 2000000000000," +
            "      \"string\": \"{(isolated)}\"" +
            "    }" +
            "]" +
            "}").build();
    }
}
