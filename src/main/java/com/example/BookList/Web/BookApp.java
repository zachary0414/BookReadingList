package com.example.BookList.Web;

import com.example.BookList.domain.Book;
import com.example.BookList.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//web层
@RestController
@RequestMapping("/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    //获取读书清单列表
    @GetMapping("/books")
    public Page<Book> getAll(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){

        //Sort sort = Sort.by(Sort.Direction.DESC,"id");
        //return bookService.findAll();
        return bookService.findAllByPage(pageable);
    }

    @PostMapping("/books")
    public Book post(Book book){
//
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);

        return bookService.save(book);
    }
    //获取一条书单信息
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable("id") long id){
        return bookService.findOne(id);
    }

    /**
     * 更新书单
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);

    }

    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id){

        bookService.deleteOne(id);

    }

    @PostMapping("/books/by")
    public int findByAuthorAndStatus(@RequestParam long id,
                                     @RequestParam long uid,
                                     @RequestParam int status){

        //return bookService.findByDescriptionEndsWith(description);
        //return bookService.findByJPQL(len);
        //return bookService.deleteByJPQL(id);
        return bookService.deleteAndUpdate(id, status, uid);
    }
}
/**
 * web层
 */
