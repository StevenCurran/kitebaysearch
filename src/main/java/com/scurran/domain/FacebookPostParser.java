package com.scurran.domain;

import com.restfb.types.Photo;
import com.restfb.types.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Steven Curran on 01/06/14.
 */
public class FacebookPostParser {

    public static List<KitebayPost> parsePosts(List<Post> posts) {
        List<KitebayPost> myPosts = new ArrayList<>();

        for (Post post : posts) {
            KitebayPost kitePost = new KitebayPostBuilder().setBrand(getBrand(post)).setProduct(getProduct(post)).setType(getType(post)).setInitialPost(post).setPostId(post.getId()).setPostImages(getImageUrls(post)).createKitebayPost();
            myPosts.add(kitePost);
        }


        return myPosts;
    }

    private static List<Photo.Image> getImageUrls(Post post) {
        return Collections.emptyList();
    }

    private static PostType getType(Post post) {
        return PostType.FOR_SALE;
    }

    private static Product getProduct(Post post) {
        return Product.LEI;
    }

    private static Brand getBrand(Post post) {
        String message = post.getMessage();
        if(message == null){
            return Brand.UNKNOWN;
        }
        for (Brand brand : Brand.values()) {
            if(message.toLowerCase().contains(brand.getBrandName().toLowerCase())){
                return brand;
            }
        }
        
        return Brand.UNKNOWN;
    }
}
