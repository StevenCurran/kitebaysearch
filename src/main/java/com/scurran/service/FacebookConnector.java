package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Ordering;
import com.restfb.*;
import com.restfb.types.Group;
import com.restfb.types.Post;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

/**
 * Created by Steven Curran on 24/05/14.
 */
@Service
public class FacebookConnector {

    private static String MY_ACCESS_TOKEN = "733433380012793";
    private static String MY_APP_SECRET = "4a11148f58820f290a554854577a9593";
    //public static FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET);
    public static FacebookClient facebookClient = new DefaultFacebookClient("CAACEdEose0cBALx8nRrMkJvRLY1ISyeNPGUoDav8tpsGSHZBYvgMfJrU45v947ZCokGj1w652ERWUr4c7ZAKZAVcAUZCyGZCHteHDjDaMW0yoHXcEI1wNL81Yvg6ZCtTuMQoW4AruRjGWQUzHKd9EWmSenjHon0CBQRfNYWcavDICS0ICZCXRHhrv1hoZApnIY7EWdCZBVL54xfwZDZD");
    public static final String KITEBAY_PID = "283893765028577";

    private static CacheLoader<String, Post> POST_CACHE_LOADER = new CacheLoader<String, Post>() {

        @Override
        public Post load(String s) throws Exception {
            return new Post();
        }
    };

    private static LoadingCache<String, Post> postCache = CacheBuilder.newBuilder().maximumSize(100).build(POST_CACHE_LOADER);

    public static List<Post> getPosts(){
        Connection<Post> kitebayPosts = facebookClient.fetchConnection(KITEBAY_PID + "/feed", Post.class, Parameter.with("limit", "100"));
        List<Post> data = kitebayPosts.getData();
        for (Post post : data) {
            postCache.put(post.getId(), post);
        }

        return kitebayPosts.getData();
    }


    @ExtDirectMethod(STORE_READ)
    public ExtDirectStoreResult<Post> getPostsExt(ExtDirectStoreReadRequest storeRequest) {

        String filterValue = null;
        if (!storeRequest.getFilters().isEmpty()) {
            StringFilter filter = (StringFilter) storeRequest.getFilters().iterator().next();
            filterValue = filter.getValue();
        }

        List<Post> users = FacebookConnector.getPosts();
        int totalSize = users.size();


        if (storeRequest.getStart() != null && storeRequest.getLimit() != null) {
            users = users.subList(storeRequest.getStart(),
                    Math.min(totalSize, storeRequest.getStart() + storeRequest.getLimit()));
        }

        return new ExtDirectStoreResult<>(totalSize, users);
    }


}
