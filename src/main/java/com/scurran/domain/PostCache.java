package com.scurran.domain;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private static LoadingCache<String, KitebayPost> postCache = CacheBuilder.newBuilder().maximumSize(250).build(POST_CACHE_LOADER);

    public synchronized static List<KitebayPost> getPosts() {
        return new ArrayList<>(postCache.asMap().values());
    }


    public static synchronized void addPosts(List<KitebayPost> posts) {

        Collections.sort(posts, new Comparator<KitebayPost>() {
            public int compare(KitebayPost o1, KitebayPost o2) {
                if (o1.getInitialPost().getUpdatedTime() == null || o2.getInitialPost().getUpdatedTime() == null)
                    return 0;
                return o1.getInitialPost().getUpdatedTime().compareTo(o2.getInitialPost().getUpdatedTime()); // may have to change this round
            }
        });

        for (KitebayPost post : posts) {
            postCache.put(post.getId(), post);
        }
    }

}
