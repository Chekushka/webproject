package webproject.api.repositories.impl;

import webproject.api.ConnectionManager;
import webproject.api.entities.Details;
import webproject.api.repositories.DetailRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
@Repository
public class DetailRepositoryImpl implements DetailRepository {
    @Override
    public Details getBook(int id) throws SQLException {
        Details det= new Details();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT  b.id, b.name, b.genre, b.description,b.author, b.year, u.id, u.email, u.phone, u.first_name, u.surname FROM public.announce_board  a JOIN public.user u ON  a.user_id=u.id JOIN  public.book b ON a.book_id = b.id WHERE a.id="+id))
        { while (resultSet.next()) {
            det=parseResultSet(resultSet);
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return det;
    }
    private Details parseResultSet(ResultSet resultSet) throws SQLException {
        Details det = new Details();
        det.setIdBook(resultSet.getInt("id"));
        det.setNameBook(resultSet.getString("name"));
        det.setGenreBook(resultSet.getString("genre"));
        det.setDescriptionBook(resultSet.getString("description"));
        det.setYearBook(LocalDate.parse(resultSet.getString("year")));
        det.setAuthorBook(resultSet.getString("author"));

        det.setIdUser(resultSet.getLong("id"));
        det.setEmailUser(resultSet.getString("email"));
        det.setPhoneUser(resultSet.getString("phone"));
        det.setFirstNameUser(resultSet.getString("first_name"));
        det.setSurnameUser(resultSet.getString("surname"));
        return det;
    }
}
