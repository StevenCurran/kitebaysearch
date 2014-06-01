package com.scurran.domain;

import com.restfb.types.Photo;
import com.restfb.types.Post;

import java.util.List;

/**
 * Created by Steven Curran on 01/06/14.
 */
public class KitebayPost {

    private String postId;
    private Post initialPost;
    private PostType type;
    private Brand brand;
    private Product product;
    private List<Photo.Image> postImages;



    public KitebayPost(String postId, Post initialPost, PostType type, Brand brand, Product product, List<Photo.Image> postImages) {
        this.postId = postId;
        this.initialPost = initialPost;
        this.type = type;
        this.brand = brand;
        this.product = product;
        this.postImages = postImages;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Post getInitialPost() {
        return initialPost;
    }

    public void setInitialPost(Post initialPost) {
        this.initialPost = initialPost;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Photo.Image> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<Photo.Image> postImages) {
        this.postImages = postImages;
    }

}
