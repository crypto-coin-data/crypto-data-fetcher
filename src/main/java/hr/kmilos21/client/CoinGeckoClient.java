package hr.kmilos21.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient(baseUri = "https://api.coingecko.com/api/v3")
public interface CoinGeckoClient {
    @GET
    @Path("/simple/price")
    Uni<Map<String, Map<String, Double>>> getPrice(
            @QueryParam("ids") String ids,
            @QueryParam("vs_currencies") String vsCurrencies
    );
}
