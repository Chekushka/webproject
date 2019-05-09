package webproject.api.repository;

import webproject.api.entities.AnnounceBoardEntity;
import webproject.api.models.AnnounceDataResponse;

import java.sql.SQLException;
import java.util.List;

public interface AnnounceBoardRepository extends GeneralRepository<AnnounceBoardEntity,Integer>{

    List<AnnounceDataResponse> selectAll2() throws SQLException;
}
