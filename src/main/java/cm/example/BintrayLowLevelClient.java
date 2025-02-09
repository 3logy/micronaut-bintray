package cm.example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.util.List;

/**
 * bintrayfetcher is copyrighted by BATOBESSE
 * Author : Ghislain Tchangang
 * Email : info@batobesse.com
 * Date : 7/21/2019
 * Time : 9:04 AM
 * Year : 2019
 */
@Singleton
public class BintrayLowLevelClient {
    private final RxHttpClient httpClient;
    private final String uri;

    public BintrayLowLevelClient(@Client(BintrayConfiguration.BINTRAY_API_URL) RxHttpClient httpClient,
                                 BintrayConfiguration configuration) {
        this.httpClient = httpClient;
        String path = "/api/{apiversion}/repos/{organization}/{repository}/packages";
        uri = UriTemplate.of(path).expand(configuration.toMap());
    }

    Maybe<List<BintrayPackage>> fetchPackages() {
        HttpRequest<?> req = HttpRequest.GET(uri);
        Flowable flowable = httpClient.retrieve(req, Argument.of(List.class, BintrayPackage.class));
        return (Maybe<List<BintrayPackage>>) flowable.firstElement();
    }
}
