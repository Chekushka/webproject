package webproject.api.repositories;

import webproject.api.entities.User;

public interface UserRepository {

    User insert(User user) throws ClassNotFoundException, Exception;

}