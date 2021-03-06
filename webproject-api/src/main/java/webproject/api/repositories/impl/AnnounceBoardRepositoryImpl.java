package webproject.api.repositories.impl;

import webproject.api.ConnectionManager;
import webproject.api.entities.AnnounceBoardEntity;
import webproject.api.models.AnnounceDataResponse;
import webproject.api.repositories.AnnounceBoardRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnnounceBoardRepositoryImpl implements AnnounceBoardRepository {
    private static final String SELECT_ALL = "SELECT * FROM public.announce_board";

    private static final String SELECT_ALL2 = "SELECT a.id, u.surname, b.name, a.announce_timestamp, a.accept FROM public.announce_board a JOIN public.\"user\" u ON  a.user_id=u.id JOIN  public.\"book\" b ON a.book_id = b.id";

    private static final String CREATE =
            "INSERT INTO public.announce_board (\"user_id\",\"book_id\",\"announce_timestamp\")\n" +
                    "VALUES(?,?,?) RETURNING id;";


    @Override
    public AnnounceBoardEntity insert(AnnounceBoardEntity entity) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE)) {
            setPreparedStatementData(statement, entity);
            try (ResultSet generatedKeys = statement.executeQuery()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        }
        return entity;
    }

    @Override
    public List<AnnounceBoardEntity> selectAll() throws SQLException {
        List<AnnounceBoardEntity> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                entities.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<AnnounceDataResponse> selectAll2() throws SQLException {
        List<AnnounceDataResponse> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL2)
        ) {
            while (resultSet.next()) {
                entities.add(parseResultSet2(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public AnnounceBoardEntity findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public int update(AnnounceBoardEntity entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(AnnounceBoardEntity entity) throws SQLException {
        return 0;
    }

    private AnnounceBoardEntity parseResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer userId = resultSet.getInt("user_id");
        Integer bookId = resultSet.getInt("book_id");
        LocalDateTime announceTS = resultSet.getTimestamp("announce_timestamp").toLocalDateTime();
        return new AnnounceBoardEntity(id, userId, bookId, announceTS);
    }
    private AnnounceDataResponse parseResultSet2(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String user = resultSet.getString("surname");
        String book = resultSet.getString("name");

        Timestamp announceTS = resultSet.getTimestamp("announce_timestamp");
        LocalDateTime dt = announceTS==null?null:announceTS.toLocalDateTime();
        String accept;
        if( resultSet.getBoolean("accept")){
             accept = "TRUE";
        }else{
            accept ="FALSE";
        }

        return new AnnounceDataResponse(id,user,book, dt,accept);
    }
    private void setPreparedStatementData(PreparedStatement statement, AnnounceBoardEntity entity) throws SQLException {
        statement.setInt(1, entity.getUserId());
        statement.setInt(2, entity.getBookId());
        statement.setTimestamp(3, Timestamp.valueOf(entity.getAnnounceTimestamp()));
    }
}