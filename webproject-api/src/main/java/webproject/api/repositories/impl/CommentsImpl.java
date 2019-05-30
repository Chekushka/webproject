package webproject.api.repositories.impl;

import webproject.api.ConnectionManager;
import webproject.api.entities.Comment;
import webproject.api.models.CommentAdd;
import webproject.api.repositories.CommentsRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentsImpl implements CommentsRepository {
    private static final String INSERT_USER_SQL = "INSERT INTO \"comment\" (user_id, book_id, comment, accept, announce_timestamp) VALUES (?, ?, ?, ?,?) RETURNING id";

    @Override
    public boolean check(int idComment) {
        System.out.println("count"+count(getBookId(idComment)));
        if(count(getBookId(idComment))>0){
            return false;
        }else{
            return true;
        }
    }
    int count (int bookId){
        int count=0;
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(accept)\n" +
                     "\tFROM public.comment \n" +
                     "\tWHERE book_id="+bookId+"and accept=true\n" +
                     "\t");
        )
        { while (resultSet.next()) {
            count=resultSet.getInt("count");
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    int getBookId(int commentId){
       int book_id=0;
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT c.book_id\n" +
                     "\tFROM public.comment c\n" +
                     "\tWHERE id="+commentId);
        )
        { while (resultSet.next()) {
            book_id =resultSet.getInt("book_id");
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return book_id;
    }

    @Override
    public CommentAdd insert(CommentAdd comment)throws SQLException {
        try (Connection dbConnection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = dbConnection.prepareStatement(INSERT_USER_SQL)) {
                statement.setInt(1,comment.getUser_id());
                statement.setInt(2,comment.getBook_id());
                statement.setString(3,comment.getComment());
                statement.setBoolean(4,comment.getAccept());
                statement.setTimestamp(5,Timestamp.valueOf(comment.getAnnounce_timestamp()));

                try (ResultSet generatedKeys = statement.executeQuery()) {
                    if (generatedKeys.next()) {
                        comment.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }

        return comment;
    }

    @Override
    public int updateBook(int idComment) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        int resultSet = statement.executeUpdate("UPDATE public.announce_board SET  accept=true WHERE book_id="+getBookId(idComment));
        return resultSet;

    }

    @Override
    public int update(int idComment)throws SQLException {
        Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             int resultSet = statement.executeUpdate("UPDATE public.comment SET  accept=true WHERE id="+idComment);
        return resultSet;
    }

    @Override
    public List<Comment> get(int id) throws SQLException {
        List<Comment> comments=new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT  c.id, u.email, c.comment, c.announce_timestamp,c.accept FROM public.comment  c JOIN public.user u ON  c.user_id=u.id WHERE c.book_id="+id);
             )
        { while (resultSet.next()) {
            comments.add(parseResultSet(resultSet));
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    private Comment parseResultSet(ResultSet resultSet) throws SQLException {
        Comment com = new Comment();

        com.setId(resultSet.getInt("id"));
        com.setUser(resultSet.getString("email"));
        com.setComment(resultSet.getString("comment"));
        com.setTimestamp((resultSet.getString("announce_timestamp")));
        if(resultSet.getBoolean("accept"))
            com.setAccept("+");
        else com.setAccept("-");
        return com;
    }

}
