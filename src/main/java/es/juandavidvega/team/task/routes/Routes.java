package es.juandavidvega.team.task.routes;

import es.juandavidvega.team.task.handlers.LoadTeamTaskHandler;
import es.juandavidvega.team.task.model.TeamTask;
import es.juandavidvega.team.task.repository.TeamTaskRepository;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.RouterFunction;
import org.springframework.web.reactive.function.ServerResponse;

import static org.springframework.http.codec.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.RequestPredicates.GET;
import static org.springframework.web.reactive.function.RequestPredicates.POST;
import static org.springframework.web.reactive.function.RouterFunctions.route;


public class Routes {
    public static RouterFunction<?> load() {
        return helloRoute()
                .and(loadTasksRoute());
    }

    private static RouterFunction<String> helloRoute() {
        return route(GET("/hello"),
                request -> ServerResponse
                        .ok()
                        .body(fromObject("Evetything when better than expected!")));
    }

    private static RouterFunction<Publisher<TeamTask>> loadTasksRoute() {
        TeamTaskRepository repository = new TeamTaskRepository();
        LoadTeamTaskHandler loadTeamTaskHandler = new LoadTeamTaskHandler(repository);
        return route(GET("/load/{team}/tasks"),
                loadTeamTaskHandler::handle);
    }
}
