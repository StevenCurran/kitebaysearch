package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
import com.scurran.domain.KitebayPost;
import com.scurran.domain.PostCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (posts.size() > 0) {
            getPostDetails("283893765028577_667349830016300");
        }

        return new ExtDirectStoreResult<>(totalSize, posts);
    }

    @ExtDirectMethod
    public KitebayPost getPostDetails(String message) {
        KitebayPost post = null;
        for (KitebayPost kitebayPost : PostCache.getPosts()) {
            if (kitebayPost.getId().equalsIgnoreCase(message)) {
                post = kitebayPost;
                break;
            }
        }

        try {

            //get the image urls of the post. just tac on the image urls here.
            Post kitebayPosts = facebookClient.fetchObject(post.getId(), Post.class, Parameter.with("fields", "object_id"));
            String objectId = kitebayPosts.getObjectId();
            //Post kitebayPostsObj = facebookClient.fetchObject(objectId, Post.class, Parameter.with("fields", "picture"));
            JsonObject kitebayPostsObj = facebookClient.fetchObject(objectId, JsonObject.class, Parameter.with("/", "picture"));

            List<String> imageUrls = new ArrayList<>();
            //imageUrls.add(kitebayPostsObj.getString("source"));

            String query = "SELECT attachment FROM stream WHERE post_id=\"" + kitebayPosts.getId() + "\"";

            System.out.println(query);
            List<JsonObject> kitebayPostsObj2 = facebookClient.executeFqlQuery(query, JsonObject.class);
            JsonArray jsonArray = kitebayPostsObj2.get(0).getJsonObject("attachment").getJsonArray("media");

            for (int i = 0; i < jsonArray.length(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);
                String src = obj.getString("src");
                src = src.replace("_s.jpg", "_n.jpg");
                imageUrls.add(src);
            }
            //fix here

            post.setPostImages(imageUrls);

        } catch (Exception e) {
            System.out.println(e);
        }

        return post;
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


    public static class MyUser {

        @Facebook
        String uid;

        @Facebook
        String url;

        public MyUser() {

        }


    }

}
