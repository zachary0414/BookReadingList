package com.example.BookList.service;

import com.example.BookList.domain.Book;
import com.example.BookList.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.*;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.BitSet;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 查询所有的书单列表
     * @return
     */
    public List<Book> findAll(){

        return bookRepository.findAll();
    }

    /**
     * 分页查询书单列表
     * @return
     */
    public Page<Book> findAllByPage(Pageable pageable){

//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        //Sort sort = new Sort.Direction(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(1,5);
        return bookRepository.findAll(pageable);
    }

    /**
     * 提交一条书单信息
     * @param book
     * @return
     */
    public Book save(Book book){
        return bookRepository.save(book);
    }

    /**
     * 获取一条读书信息
     * @param id
     * @return
     */
    public Book findOne(long id){
        return bookRepository.findById(id).get();
    }

    /**
     * 删除一条读书信息
     * @param id
     */
    public void deleteOne(long id){
        bookRepository.deleteById(id);

    }

    /**
     * 根据author查询书单列表
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author){

        return bookRepository.findByAuthor(author);
    }

    /**
     * 根据author&status查询书单列表
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status){
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    public List<Book> findByDescriptionEndsWith(String des){
        return bookRepository.findByDescriptionEndsWith(des);
    }

    /**
     * 自定义查询
     * @param len
     * @return
     */
    public List<Book> findByJPQL(int len){
        return bookRepository.findJPQL(len);
    }

    /**
     * 自定义更新
     * @param status
     * @param id
     * @return
     */

    public int updateByJPQL(int status, long id){
        return bookRepository.updateByJPQL(status, id);
    }

    /**
     * 自定义删除
     * @param id
     * @return
     */
    public int deleteByJPQL(long id){
        return bookRepository.deleteByJPQL(id);
    }

    /**
     * 测试事务操作方法
     * @param id
     * @param status
     * @param uid
     * @return
     */
    @Transactional
    //加了事务报错之前的不会change
    public int deleteAndUpdate(long id, int status, long uid){

        int dcount = bookRepository.deleteByJPQL(id);

        int ucount = bookRepository.updateByJPQL(status, uid);

        return dcount + ucount;
    }
}
