package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.PostService;
import com.blog.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    private EmailService emailService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // http://localhost:8080/api/posts
   // @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @PostMapping   // Create Post
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
    //@PreAuthorize ( "hasRole('ROLE_ADMIN','ROLE_USER')" )
    @GetMapping   //Get All Post List
    public PostResponse getAllPosts(@RequestParam(value = "pageNo" , defaultValue = "0", required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(value="sortBy", defaultValue = "id",required = false) String sortBy,
                                    @RequestParam(value="sortDir", defaultValue = "asc", required = false)String sortDir)  {

//        PostResponse list = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
//        return list;
         return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // http://localhost:8080/api/posts/id
   // @PreAuthorize ( "hasRole('ROLE_ADMIN','ROLE_USER')" )
    @GetMapping("/{id}")     // Get a Post By Id
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id)  {
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    // http://localhost:8080/api/posts/id
    //@PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    @PutMapping("/{id}")    // Update Post By Id
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto dtoUpdated = postService.updatePostById(postDto, id);
        return ResponseEntity.ok(dtoUpdated);
    }

    // http://localhost:8080/api/posts/id
    //@PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Data Successfully Deleted",HttpStatus.OK);
    }

}
