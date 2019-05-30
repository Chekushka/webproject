package webproject.api.repositories;

import webproject.api.entities.Details;

public interface DetailRepository {
     Details getBook (int id) throws ClassNotFoundException, Exception;
}
