package webproject.api.models;

import java.time.LocalDateTime;

public class AnnounceDataResponse {
    private Integer id;
    private String user;
    private String book;
    private LocalDateTime announceTimestamp;
    private String accept;

    public AnnounceDataResponse(Integer id, String user, String book, LocalDateTime announceTimestamp, String accept) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.announceTimestamp = announceTimestamp;
        this.accept = accept;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public LocalDateTime getAnnounceTimestamp() {
        return announceTimestamp;
    }

    public void setAnnounceTimestamp(LocalDateTime announceTimestamp) {
        this.announceTimestamp = announceTimestamp;
    }
}
