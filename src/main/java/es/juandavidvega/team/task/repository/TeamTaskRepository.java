package es.juandavidvega.team.task.repository;

import es.juandavidvega.team.task.model.Team;
import es.juandavidvega.team.task.model.TeamTask;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamTaskRepository {
    private static final Map<Team, Flux<TeamTask>> tasks = tasksByTeam();

    public Flux<TeamTask> findTaskBy(Team team) {
        return tasks.get(team);
    }

    private static HashMap<Team, Flux<TeamTask>> tasksByTeam() {
        return new HashMap<Team, Flux<TeamTask>>(){{
           put(Team.allblacks, sampleTasks(Team.allblacks));
           put(Team.newteam, sampleTasks(Team.newteam));
           put(Team.solfamidas, sampleTasks(Team.solfamidas));
           put(Team.ghostbuster, sampleTasks(Team.ghostbuster));
        }};
    }

    private static Flux<TeamTask> sampleTasks(Team team) {
        List<TeamTask> tasks = Arrays.asList(
                new TeamTask(LocalDateTime.now().withDayOfMonth(15), "Task A " + team.toString()),
                new TeamTask(LocalDateTime.now().withDayOfMonth(16), "Task B " + team.toString()),
                new TeamTask(LocalDateTime.now().withDayOfMonth(17), "Task C " + team.toString()),
                new TeamTask(LocalDateTime.now().withDayOfMonth(18), "Task D " + team.toString()));
        return Flux.fromIterable(tasks);
    }
}
