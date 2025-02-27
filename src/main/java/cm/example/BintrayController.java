package cm.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import java.util.List;

/**
 * bintrayfetcher is copyrighted by BATOBESSE
 * Author : Ghislain Tchangang
 * Email : info@batobesse.com
 * Date : 7/21/2019
 * Time : 9:05 AM
 * Year : 2019
 */
@Controller("/bintray")
public class BintrayController {
    private final BintrayLowLevelClient bintrayLowLevelClient;

    private final BintrayClient bintrayClient;

    public BintrayController(BintrayLowLevelClient bintrayLowLevelClient,
                             BintrayClient bintrayClient) {
        this.bintrayLowLevelClient = bintrayLowLevelClient;
        this.bintrayClient = bintrayClient;
    }

    @Get("/packages-lowlevel")
    Maybe<List<BintrayPackage>> packagesWithLowLevelClient() {
        return bintrayLowLevelClient.fetchPackages();
    }

    @Get(uri = "/packages", produces = MediaType.APPLICATION_JSON_STREAM)
    Flowable<BintrayPackage> packages() {
        return bintrayClient.fetchPackages();
    }
}
