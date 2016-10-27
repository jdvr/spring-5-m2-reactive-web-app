package es.juandavidvega.team.task.handlers;


import es.juandavidvega.team.task.model.Team;
import es.juandavidvega.team.task.model.TeamTask;
import es.juandavidvega.team.task.repository.TeamTaskRepository;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.ServerRequest;
import org.springframework.web.reactive.function.ServerResponse;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoadTeamTaskHandler {

    private final TeamTaskRepository repository;

    public LoadTeamTaskHandler(TeamTaskRepository repository) {
        this.repository = repository;
    }

    public ServerResponse<Publisher<TeamTask>> byTeam(ServerRequest request) {
        Team team = Team.valueOf(request.pathVariable("team"));
        Flux<TeamTask> tasks = repository.findTaskBy(team);
        return ServerResponse.ok().body(tasks, TeamTask.class);
    }

    public ServerResponse<Publisher<TeamTask>> byDay(ServerRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime localDateTime = request.queryParam("limitDay")
                                                .map(rawDate -> LocalDate.parse(rawDate, formatter))
                                                .map(LocalDate::atStartOfDay)
                                                .orElse(LocalDateTime.now());
        Flux<TeamTask> tasks = repository.findTaskBy(localDateTime);
        return ServerResponse.ok().body(tasks, TeamTask.class);
    }
}
