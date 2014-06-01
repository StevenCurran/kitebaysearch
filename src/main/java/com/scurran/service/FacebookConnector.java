package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

/**
 * Created by Steven Curran on 24/05/14.
 */
@Service
public class FacebookConnector {

    private static String MY_ACCESS_TOKEN = "733433380012793";
    private static String MY_APP_SECRET = "4a11148f58820f290a554854577a9593";
    //public static FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET);
    public static FacebookClient facebookClient = new DefaultFacebookClient("CAACEdEose0cBAIlZCvZAraDfovxJd53Rv6hq8RmR56CSEY2jipG957HShp5MwWgb50rT0nxDZA2JTgcnstX41ZAld2SKfBombZAOAptqMRR204kbO6O8rZBTCx6lLBMZCORcHX3taiZAPvsCyIHPJwlXmhh6ndOhlmJQ6oeiHZAGt7GWYZC76eVPV3528p8NH9z3PmE6kbv7yVnwZDZD");
    public static final String KITEBAY_PID = "283893765028577";

    private static CacheLoader<String, Post> POST_CACHE_LOADER = new CacheLoader<String, Post>() {

        @Override
        public Post load(String s) throws Exception {
            return new Post();
        }
    };

    private static LoadingCache<String, Post> postCache = CacheBuilder.newBuilder().maximumSize(100).build(POST_CACHE_LOADER);

    public static List<Post> getPosts() {
        Connection<Post> kitebayPosts = facebookClient.fetchConnection(KITEBAY_PID + "/feed", Post.class, Parameter.with("limit", "100"));
        List<Post> data = kitebayPosts.getData();
        for (Post post : data) {
            postCache.put(post.getId(), post);
        }

        return kitebayPosts.getData();
        //http://stackoverflow.com/questions/16419144/get-picture-object-from-facebook-post-with-graph-api
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
