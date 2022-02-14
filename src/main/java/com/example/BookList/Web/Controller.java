        package com.example.BookList.Web;

        import com.example.BookList.domain.Book;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        @RestController
        //@ControllerAdvice
        @RequestMapping("/v0")
        public class Controller {

//            @Autowired
//            //注入对象
//            private Book book;
           // @RequestMapping(value = "/say",method = RequestMethod.GET)
            @PostMapping("/say")
            public String HelloController(){
                return "Spring boot";
            }

            @GetMapping("/books")
            //@ResponseBody
            public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size",defaultValue = "10") int size){
                Map<String,Object> book1 = new HashMap<String, Object>();
                book1.put("name","简爱");
                book1.put("isbn", "9765418");
                book1.put("author", "勃朗特");
                Map<String,Object> book2 = new HashMap<String, Object>();
                book2.put("name","我承认");
                book2.put("isbn", "12348247");
                book2.put("author", "蒋方舟");

                List<Map> contents = new ArrayList<>();
                contents.add(book1);
                contents.add(book2);

                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("page", page);
                pageMap.put("size", size);
                pageMap.put("content", contents);
                return contents;
            }

            @GetMapping("/books/{id}")
            public Object getOne(@PathVariable long id){

                //return book;
                return null;
            }
            @PostMapping("/books")
            public Object post(@RequestParam("name") String name,
                               @RequestParam("author") String author,
                               @RequestParam("isbn") String isbn){
                Map<String,Object> book = new HashMap<String, Object>();
                book.put("name",name);
                book.put("author", author);
                book.put("isbn", isbn);
                return book;
            }
        }



