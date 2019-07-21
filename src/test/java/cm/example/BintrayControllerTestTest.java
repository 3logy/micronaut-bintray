package cm.example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class BintrayControllerTestTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    @Client("/")
    RxStreamingHttpClient rxHttpClient;

    List<String> expectedProfileNames = Arrays.asList("base", "federation", "function", "function-aws", "service");

    @Test
    void testItWorks() {
        assertTrue(embeddedServer.isRunning());
    }

    @Test
    void fetchPackagesWithLowLevelClient(){
        HttpRequest request = HttpRequest.GET("/bintray/packages-lowlevel");
        HttpResponse resp = rxHttpClient.toBlocking().exchange(request, Argument.of(List.class, BintrayPackage.class));
        assertEquals(resp.status(), HttpStatus.OK);
        assertNotNull(resp.body());

        List<BintrayPackage> packages = (List<BintrayPackage>) resp.body();
        List <String> packagesName = new ArrayList<>();
        for(BintrayPackage elt : packages) {
            packagesName.add(elt.name);
        }

        assertTrue(packagesName.containsAll(expectedProfileNames));
    }

    @Test
    void fetchPackagesWithDeclarativeClient(){
        HttpRequest request = HttpRequest.GET("/bintray/packages");
        Flowable<BintrayPackage> bintrayPackageStream = rxHttpClient.jsonStream(request, BintrayPackage.class);
        Iterable<BintrayPackage> bintrayPackages = bintrayPackageStream.blockingIterable();
        List<String> bintrayNames = new ArrayList<>();
        for(BintrayPackage elt: bintrayPackages){
            bintrayNames.add(elt.name);
        }

        assertTrue(bintrayNames.containsAll(expectedProfileNames));
    }
}
