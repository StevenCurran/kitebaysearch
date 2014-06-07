package com.scurran.domain;

import com.restfb.types.Photo;
import com.restfb.types.Post;

import java.util.Date;
import java.util.List;

/**
 * Created by Steven Curran on 01/06/14.
 */
public class KitebayPost extends Post {

    private String postId;
    private Post initialPost;
    private PostType type;
    private Brand brand;
    private Product product;
    private List<Photo.Image> postImages;
/*



myPhoto = new Ext.Component({
    autoEl: { tag: 'img', src: 'image.gif'}
});

myPhoto.el.dom.src = newImageSource;

yourCarousel = Ext.getCmp('your_carousel_id');
store.each(function(record){
                    yourCarousel.add({
                        html: '<img src=' + record.get('url') + '/>'
                    });
                });
 */


    public KitebayPost(String postId, Post initialPost, PostType type, Brand brand, Product product, List<Photo.Image> postImages) {
        this.postId = postId;
        this.initialPost = initialPost;
        this.type = type;
        this.brand = brand;
        this.product = product;
        this.postImages = postImages;
    }

    public KitebayPost() {

    }

    public KitebayPost(Post user) {
        this.initialPost = user;
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

    public String getPostType() {
        return type.getType();
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand.getBrandName();
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getProduct() {
        return product.getTerm();
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

    @Override
    public String getMessage() {
        return initialPost.getMessage();
    }

    @Override
    public String getId() {
        return initialPost.getId();
    }

    @Override
    public Date getUpdatedTime() {
        return initialPost.getUpdatedTime();
    }

    @Override
    public String getLink() {
        return initialPost.getActions().get(0).getLink();
    }
}
