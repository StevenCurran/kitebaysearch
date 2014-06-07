package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.scurran.domain.KitebayPost;
import com.scurran.domain.PostCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        if (storeRequest.getParams().size() > 0) {
            Map<String, Object> params = storeRequest.getParams();
            posts = filter(params, posts);
        }

        int totalSize = posts.size();


        if (storeRequest.getStart() != null && storeRequest.getLimit() != null) {
            posts = posts.subList(storeRequest.getStart(),
                    Math.min(totalSize, storeRequest.getStart() + storeRequest.getLimit()));
        }

        return new ExtDirectStoreResult<>(totalSize, posts);
    }

    //private List<KitebayPost> filter(Map<String, Object> params, List<KitebayPost> posts) {

    private List<KitebayPost> filter(Map<String, Object> params, List<KitebayPost> posts) {
        final Map<String, Object> localParams = params;

        Iterable<KitebayPost> filter = Iterables.filter(posts, new Predicate<KitebayPost>() {
            @Override
            public boolean apply(KitebayPost kitebayPost) {
                if (localParams.get("leaf").equals(false)) {
                    return true;
                } else {

                    if (localParams.get("parent").equals("brand")) {
                        if (kitebayPost.getBrand().equalsIgnoreCase((String) localParams.get("node"))) {
                            return true;// may neeed to invert
                        } else {
                            return false;
                        }

                    } else {
                        String node = (String) localParams.get("node");
                        if (kitebayPost.getProductEnum().name().equalsIgnoreCase(node)) {
                            return true;// may neeed to invert
                        } else {
                            return false;
                        }


                    }
                }


            }
        });


        List<KitebayPost> newPosts = Lists.newArrayList(filter);

        return newPosts;
    }


}
