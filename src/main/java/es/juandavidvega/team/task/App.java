package es.juandavidvega.team.task;


import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.RouterFunction;
import org.springframework.web.reactive.function.RouterFunctions;
import org.springframework.web.reactive.function.ServerResponse;
import reactor.ipc.netty.http.HttpServer;

import static org.springframework.http.codec.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.RequestPredicates.GET;
import static org.springframework.web.reactive.function.RouterFunctions.route;

public class App {

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.start();
    }

    private void start() throws InterruptedException {
        RouterFunction<?> routes =
                route(GET("/hello"),
                        request -> ServerResponse
                                .ok()
                                .body(fromObject("Evetything when better than expected!")));

        HttpHandler handler = RouterFunctions.toHttpHandler(routes);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        HttpServer server = HttpServer.create("localhost", 8080);
        server.startAndAwait(adapter);

    }


}
