package com.example.BookList.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Override
    Page<Book> findAll(Pageable pageable);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionEndsWith(String des);

    /**
     * 自定义查询
     * @param len
     * @return
     */
    //@Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book b where LENGTH(name) > ?1", nativeQuery = true)
    List<Book> findJPQL(int len);
    /**
     * 自定义更新
     */
    @Transactional
    @Modifying
    @Query("update Book b set b.status =?1 where b.id = ?2")
    int updateByJPQL(int status, long id);

    @Transactional
    @Modifying
    @Query(value = "delete Book b where b.id = ?1")
    int deleteByJPQL(long id);


}

