package cm.example;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;

/**
 * bintrayfetcher is copyrighted by BATOBESSE
 * Author : Ghislain Tchangang
 * Email : info@batobesse.com
 * Date : 7/21/2019
 * Time : 9:05 AM
 * Year : 2019
 */
@Client(BintrayConfiguration.BINTRAY_API_URL)
public interface BintrayClient {
    @Get("/api/${bintray.apiversion}/repos/${bintray.organization}/${bintray.repository}/packages")
    Flowable<BintrayPackage> fetchPackages();
}
