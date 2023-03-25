package com.blog.controller;

import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import com.blog.service.PostService;
import com.blog.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    @Autowired
    private EmailService emailService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // http://localhost:8080/api/post/1/comments
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<Object> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto, BindingResult
                                                     result){

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto Dto = commentService.createComment(postId, commentDto);
        emailService.sendEmail(commentDto.getEmail(), "Comment Posted Successfully", "Thank you Mr/Mrs "+commentDto.getName()+" "+commentDto.getBody()+"\n"+"\nThis is your comment");
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);
       // return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/post/1/comments
    @GetMapping("/post/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable long postId){

        return commentService.getCommentsByPostId(postId);
    }

    // http://localhost:8080/api/post/1/comments/1
    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId){

        CommentDto dto = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    // http://localhost:8080/api/post/1/comments/1
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId, @RequestBody CommentDto commentDto){

        CommentDto dto = commentService.updateCommentById(postId, commentId, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    // http://localhost:8080/api/post/1/comments/1
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId){

        commentService.deleteCommentById(postId, commentId);

        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);

    }
}
