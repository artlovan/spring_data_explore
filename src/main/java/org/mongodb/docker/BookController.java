package org.mongodb.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        Book savedBook = repository.save(book);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Book book = repository.findById(id);

        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

}
