package com.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title must contain atleast 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post discription must contain atleast 10 characters")
    private String discription;
    @NotEmpty
    @Size(min = 10, message = "Post content must contain atleast 10 characters")
    private String content;

}
