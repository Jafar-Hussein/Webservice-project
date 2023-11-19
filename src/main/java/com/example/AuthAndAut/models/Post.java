package com.example.AuthAndAut.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer id;
    private String title;
    @Column(name = "description")
    private String description;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_post_junction", joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
@ManyToMany(mappedBy = "posts", fetch = FetchType.EAGER)
private Set<User> users;

}
