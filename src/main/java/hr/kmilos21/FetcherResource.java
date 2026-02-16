package hr.kmilos21;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/")
public class FetcherResource {
    @GET
    @Path("/price")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<String>> getCryptoCoinInfo(@QueryParam("currency") String curr) {
        if (curr == null){
            return Uni.createFrom().item(
                    RestResponse.status(RestResponse.Status.BAD_REQUEST, "Missing currency")
            );
        }
        return null;
    }

}
