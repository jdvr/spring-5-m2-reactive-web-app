package es.juandavidvega.team.task.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeamTask {

    private final LocalDateTime localDateTime;
    private final String title;

    public TeamTask(LocalDateTime localDateTime, String title) {

        this.localDateTime = localDateTime;
        this.title = title;
    }

    public String getDeadLine() {
        return localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String getTitle() {
        return title;
    }
}
