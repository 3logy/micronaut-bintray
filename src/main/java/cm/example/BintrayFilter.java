package cm.example;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

/**
 * bintrayfetcher is copyrighted by BATOBESSE
 * Author : Ghislain Tchangang
 * Email : info@batobesse.com
 * Date : 7/21/2019
 * Time : 10:01 AM
 * Year : 2019
 */
@Filter("/api/${bintray.apiversion}/repos/**")
@Requires(property = BintrayConfiguration.PREFIX + ".username")
@Requires(property = BintrayConfiguration.PREFIX + ".token")
public class BintrayFilter { //implements HttpClientFilter {

    private final BintrayConfiguration configuration;

    public BintrayFilter(BintrayConfiguration config){
        this.configuration = config;
    }

    //@Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return null;
    }
}
