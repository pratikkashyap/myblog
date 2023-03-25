package com.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;
    @NotEmpty(message = "Enter Characters only(A-Z,a-z")
    @Size(min = 2, message = "Naam to sahi likh de be")
    private String name;
    @NotEmpty(message = "Enter Valid Email Address")
    @Email
    private String email;
    @NotEmpty(message = "Enter atleast 5 characters")
    @Size(min = 5, message = "Accha sa comment kar be")
    private String body;

}
