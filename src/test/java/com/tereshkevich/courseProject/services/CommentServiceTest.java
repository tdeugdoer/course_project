package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Comment;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository mockCommentRepository;

    private CommentService commentServiceUnderTest;

    @BeforeEach
    void setUp() {
        commentServiceUnderTest = new CommentService(mockCommentRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure CommentRepository.findAll(...).
        final Comment comment = new Comment();
        comment.setId(0);
        comment.setText("text");
        comment.setPerson("person");
        final Product product = new Product();
        product.setId(0);
        comment.setProduct(product);
        final List<Comment> comments = List.of(comment);
        when(mockCommentRepository.findAll()).thenReturn(comments);

        // Run the test
        final List<Comment> result = commentServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_CommentRepositoryReturnsNoItems() {
        // Setup
        when(mockCommentRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Comment> result = commentServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final Comment comment = new Comment();
        comment.setId(0);
        comment.setText("text");
        comment.setPerson("person");
        final Product product = new Product();
        product.setId(0);
        comment.setProduct(product);

        // Run the test
        commentServiceUnderTest.save(comment);

        // Verify the results
        verify(mockCommentRepository).save(any(Comment.class));
    }

    @Test
    void testUpdate() {
        // Setup
        final Comment updatedComment = new Comment();
        updatedComment.setId(0);
        updatedComment.setText("text");
        updatedComment.setPerson("person");
        final Product product = new Product();
        product.setId(0);
        updatedComment.setProduct(product);

        // Run the test
        commentServiceUnderTest.update(0, updatedComment);

        // Verify the results
        verify(mockCommentRepository).save(any(Comment.class));
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        commentServiceUnderTest.delete(0);

        // Verify the results
        verify(mockCommentRepository).deleteById(0);
    }
}
