package com.example.bookmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bookmanage.common.R;
import com.example.bookmanage.entity.Book;
import com.example.bookmanage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @PostMapping
    public R save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        return flag ? R.success("新增成功") : R.error("新增失败");
    }
    
    @PutMapping
    public R update(@RequestBody Book book) {
        boolean flag = bookService.updateById(book);
        return flag ? R.success("修改成功") : R.error("修改失败");
    }
    
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean flag = bookService.removeById(id);
        return flag ? R.success("删除成功") : R.error("删除失败");
    }
      @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        return book != null ? R.success(book, "查询成功") : R.error("查询失败");
    }
    
    @GetMapping
    public R getAll() {
        return R.success(bookService.list());
    }
    
    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, String name) {
        IPage<Book> page = bookService.getPage(currentPage, pageSize, name);
        if (currentPage > page.getPages()) {
            page = bookService.getPage((int) page.getPages(), pageSize, name);
        }
        return R.success(page);
    }
}