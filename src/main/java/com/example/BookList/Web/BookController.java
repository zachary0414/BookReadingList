package com.example.BookList.Web;

import com.example.BookList.domain.Book;
import com.example.BookList.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取书单列表
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){

        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page",page1);
        return "books";
    }

    /**
     * 获取书单详细信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "book";
    }

    /**
     * 跳转input页面
     * @return
     */
    @GetMapping("/books/input")
    public String toInputPage(Model model){
        model.addAttribute("book",new Book());
        return "input";
    }

    /**
     * 跳转到更新页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "input";
    }

    /**
     * 添加一个书单信息
     * @param book
     * @return
     */
    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes){
        Book book1 = bookService.save(book);
        if (book1 != null)
            attributes.addFlashAttribute("message", "《" + book1.getName() + "》信息提交成功！");
        return "redirect:/books";
    }
    /**
     *
     */
    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, final RedirectAttributes attributes){
        bookService.deleteOne(id);
        attributes.addFlashAttribute("message", "删除成功！");
        return "redirect:/books";
    }



}
