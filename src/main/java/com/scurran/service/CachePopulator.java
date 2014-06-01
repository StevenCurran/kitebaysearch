package com.scurran.service;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.scurran.domain.KitebayPost;
import com.scurran.domain.PostCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Steven Curran on 01/06/14.
 */

@Component
public class CachePopulator {

    private static String MY_ACCESS_TOKEN = "733433380012793";
    private static String MY_APP_SECRET = "4a11148f58820f290a554854577a9593";
    public static final String KITEBAY_PID = "283893765028577";


    public CachePopulator() {
        TimerTask populationTask = new PopulationTask();
        Timer timer = new Timer(true);
        timer.schedule(populationTask, 0, 1800000); // get new posts every 30 mins
    }


    public class PopulationTask extends TimerTask {

        @Override
        public void run() {
            populate();
        }

        private void populate() {

            FacebookClient facebookClient = new DefaultFacebookClient(new DefaultFacebookClient().obtainAppAccessToken(MY_ACCESS_TOKEN, MY_APP_SECRET).getAccessToken());
            Connection<Post> kitebayPosts = facebookClient.fetchConnection(KITEBAY_PID + "/feed", Post.class, Parameter.with("limit", "20"));

            List<KitebayPost> posts = new ArrayList<>();
            for (Post post : kitebayPosts.getData()) {
                posts.add(new KitebayPost(post));
            }

            PostCache.addPosts(posts);
        }
    }


}


