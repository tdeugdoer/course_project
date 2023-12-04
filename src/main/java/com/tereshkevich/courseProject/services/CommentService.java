package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Comment;
import com.tereshkevich.courseProject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void update(int id, Comment updatedComment) {
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
    }

    @Transactional
    public void delete(int id) {
        commentRepository.deleteById(id);
    }
}