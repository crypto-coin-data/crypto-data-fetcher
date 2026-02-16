package hr.kmilos21.schedule;

import hr.kmilos21.client.CoinGeckoClient;
import hr.kmilos21.entity.CryptoCoin;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CryptoScheduler {

    @RestClient
    CoinGeckoClient geckoClient;

    @Scheduled(every = "60s")
    void fetchAndSave() {
        geckoClient.getPrice("bitcoin", "eur")
                .onItem().transformToUni(data -> {
                    Double price = data.get("bitcoin").get("eur");

                    return Panache.withTransaction(() ->
                            CryptoCoin.<CryptoCoin>find("currency", "BTC").firstResult()
                                    .onItem().ifNotNull().invoke(coin -> {
                                        coin.setValues(price);
                                    })
                    );
                })
                .subscribe().with(
                        item -> {},
                        failure -> {
                            System.out.println("Failure: " + failure.getMessage());
                        }
                );
    }
}
