package webproject.api.models;

import java.time.LocalDateTime;

public class CommentAdd {
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private String comment ;
    private Boolean accept;
    private LocalDateTime announce_timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public LocalDateTime getAnnounce_timestamp() {
        return announce_timestamp;
    }

    public void setAnnounce_timestamp(LocalDateTime announce_timestamp) {
        this.announce_timestamp = announce_timestamp;
    }
}
