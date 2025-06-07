package com.example.bookmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookmanage.entity.Book;
import com.example.bookmanage.mapper.BookMapper;
import com.example.bookmanage.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    
    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage<Book> page = new Page<>(currentPage, pageSize);
        return baseMapper.selectPage(page, null);
    }
    
    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, String name) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null && !name.isEmpty(), Book::getName, name);
        IPage<Book> page = new Page<>(currentPage, pageSize);
        return baseMapper.selectPage(page, lqw);
    }
}