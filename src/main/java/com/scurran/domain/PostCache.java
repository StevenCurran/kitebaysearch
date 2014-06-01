package com.scurran.domain;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
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

        for (KitebayPost post : posts) {
            postCache.put(post.getId(), post);
        }
    }

}
