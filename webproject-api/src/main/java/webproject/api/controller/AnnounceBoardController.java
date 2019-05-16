package webproject.api.controller;

import webproject.api.entities.AnnounceBoardEntity;
import webproject.api.models.AnnounceAddRequest;
import webproject.api.models.AnnounceAddResponse;
import webproject.api.models.AnnounceDataResponse;
import webproject.api.repository.impl.AnnounceBoardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class AnnounceBoardController {
    @Autowired
    AnnounceBoardRepositoryImpl announceBoardRepository;


    @GetMapping(value = "/api/announce")
    public ResponseEntity<List<AnnounceDataResponse>> getAllUsers() throws SQLException {
        List<AnnounceDataResponse> announceBoardEntities = announceBoardRepository.selectAll2();
        return new ResponseEntity<>(announceBoardEntities, HttpStatus.OK);
    }

    @PostMapping("/api/announce/add")
    public ResponseEntity register(@RequestBody AnnounceAddRequest request) throws Exception {
        AnnounceBoardEntity announceBoardEntity = new AnnounceBoardEntity();
        announceBoardEntity.setBookId(request.getBookId());
        announceBoardEntity.setUserId(request.getUserId());
        announceBoardEntity.setAnnounceTimestamp(LocalDateTime.now());
        AnnounceBoardEntity insertedAnnounceBoardEntity = announceBoardRepository.insert(announceBoardEntity);
        AnnounceAddResponse regResponse = new AnnounceAddResponse();
        regResponse.setId(insertedAnnounceBoardEntity.getId());
        return new ResponseEntity<>(regResponse, HttpStatus.OK);
    }

}
