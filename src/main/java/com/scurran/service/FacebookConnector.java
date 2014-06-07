package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.scurran.domain.KitebayPost;
import com.scurran.domain.PostCache;
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
    public static FacebookClient facebookClient = new DefaultFacebookClient(new DefaultFacebookClient().obtainAppAccessToken(MY_ACCESS_TOKEN, MY_APP_SECRET).getAccessToken());

    public static final String KITEBAY_PID = "283893765028577";

    public static List<Post> getPosts() {
        Connection<Post> kitebayPosts = facebookClient.fetchConnection(KITEBAY_PID + "/feed", Post.class, Parameter.with("limit", "100"));

        return kitebayPosts.getData();
        //http://stackoverflow.com/questions/16419144/get-picture-object-from-facebook-post-with-graph-api
    }


    @ExtDirectMethod(STORE_READ)
    public ExtDirectStoreResult<KitebayPost> getPostsExt(ExtDirectStoreReadRequest storeRequest) {

        String filterValue = null;
        if (!storeRequest.getFilters().isEmpty()) {
            StringFilter filter = (StringFilter) storeRequest.getFilters().iterator().next();
            filterValue = filter.getValue();
        }

        //List<Post> users = FacebookConnector.getPosts();
        List<KitebayPost> posts = PostCache.getPosts();

        int totalSize = posts.size();


        if (storeRequest.getStart() != null && storeRequest.getLimit() != null) {
            posts = posts.subList(storeRequest.getStart(),
                    Math.min(totalSize, storeRequest.getStart() + storeRequest.getLimit()));
        }

        return new ExtDirectStoreResult<>(totalSize, posts);
    }


}
