package com.scurran.domain;

import com.restfb.types.Photo;
import com.restfb.types.Post;

public class KitebayPostBuilder {
    private String postId;
    private Post initialPost;
    private PostType type;
    private Brand brand;
    private Product product;


    public KitebayPostBuilder setPostId(String postId) {
        this.postId = postId;
        return this;
    }

    public KitebayPostBuilder setInitialPost(Post initialPost) {
        this.initialPost = initialPost;
        return this;
    }

    public KitebayPostBuilder setType(PostType type) {
        this.type = type;
        return this;
    }

    public KitebayPostBuilder setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public KitebayPostBuilder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public KitebayPost createKitebayPost() {
        return new KitebayPost(postId, initialPost, type, brand, product);
    }
}