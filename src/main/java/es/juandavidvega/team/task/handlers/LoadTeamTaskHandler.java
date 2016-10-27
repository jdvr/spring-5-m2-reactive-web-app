package es.juandavidvega.team.task.handlers;


import es.juandavidvega.team.task.model.Team;
import es.juandavidvega.team.task.model.TeamTask;
import es.juandavidvega.team.task.repository.TeamTaskRepository;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.ServerRequest;
import org.springframework.web.reactive.function.ServerResponse;
import reactor.core.publisher.Flux;

public class LoadTeamTaskHandler {

    private final TeamTaskRepository repository;

    public LoadTeamTaskHandler(TeamTaskRepository repository) {
        this.repository = repository;
    }

    public ServerResponse<Publisher<TeamTask>> handle(ServerRequest request) {
        Team team = Team.valueOf(request.pathVariable("team"));
        Flux<TeamTask> tasks = repository.findTaskBy(team);
        return ServerResponse.ok().body(tasks, TeamTask.class);
    }
}
