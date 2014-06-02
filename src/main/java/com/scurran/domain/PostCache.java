package com.scurran.domain;

import com.google.common.cache.CacheLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Steven Curran on 01/06/14.
 */
public class PostCache {


    private static CacheLoader<String, KitebayPost> POST_CACHE_LOADER = new CacheLoader<String, KitebayPost>() {

        @Override
        public KitebayPost load(String s) throws Exception {
            return new KitebayPost();
        }
    };

    //private static LoadingCache<String, KitebayPost> postCache = CacheBuilder.newBuilder().maximumSize(250).build(POST_CACHE_LOADER);


    private static List<KitebayPost> postCache = Collections.synchronizedList(new ArrayList<KitebayPost>());

    public synchronized static List<KitebayPost> getPosts() {
        /*
        ArrayList<KitebayPost> posts = new ArrayList<>(postCache.asMap().values());

        Collections.sort(posts, new Comparator<KitebayPost>() {
            public int compare(KitebayPost o1, KitebayPost o2) {
                if (o1.getInitialPost().getUpdatedTime() == null || o2.getInitialPost().getUpdatedTime() == null)
                    return 0;
                return o2.getInitialPost().getUpdatedTime().compareTo(o1.getInitialPost().getUpdatedTime()); // may have to change this round
            }
        });
        */

        return postCache;
    }


    public static synchronized void addPosts(List<KitebayPost> posts) {
        postCache.clear();
        postCache.addAll(posts);
    }

}
