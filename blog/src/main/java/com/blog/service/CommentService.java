package com.blog.service;

import com.blog.payload.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto);

    void deleteCommentById(long postId, long commentId);
}
