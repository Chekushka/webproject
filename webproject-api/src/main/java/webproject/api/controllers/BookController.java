package webproject.api.controllers;


import webproject.api.entities.AnnounceBoardEntity;
import webproject.api.entities.BookEntity;
import webproject.api.models.BookRegRequest;
import webproject.api.models.BookRegResponse;
import webproject.api.repositories.BookRepository;
import webproject.api.repositories.impl.AnnounceBoardRepositoryImpl;
import webproject.api.repositories.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    AnnounceBoardRepositoryImpl announceBoardRepository;
    public BookController() {
        this.bookRepository = new BookRepositoryImpl();
    }
    @PostMapping("/api/book/register")
    public BookRegResponse register(@RequestBody BookRegRequest regRequest) throws Exception {
        BookEntity newBook= new BookEntity();
        newBook.setName(regRequest.getName());
        newBook.setAuthor(regRequest.getAuthor());
        newBook.setGenre(regRequest.getGenre());
        newBook.setYear(regRequest.getYear());
        newBook.setDescription(regRequest.getDescription());


        BookEntity insertedBook=this.bookRepository.insert(newBook);
        BookRegResponse regResponse= new BookRegResponse();
        regResponse.setBookId(insertedBook.getId());
        add_to_board(insertedBook);


        return regResponse;
    }
    public AnnounceBoardEntity add_to_board(BookEntity entity)throws Exception{
        AnnounceBoardEntity new_book= new AnnounceBoardEntity();
        new_book.setUserId(1);
        new_book.setBookId(entity.getId());
        LocalDateTime now = LocalDateTime.now();
        new_book.setAnnounceTimestamp(now);
        new_book.setAccept("-");
        AnnounceBoardEntity inserted_element=this.announceBoardRepository.insert(new_book);
        return new_book;
    }
}
