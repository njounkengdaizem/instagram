package com.example.instagram.fragments

import android.util.Log
import com.example.instagram.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFrament: FeedFragment(){
    override fun queryPosts(){

            val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
            //Find all post objects
            query.include(Post.KEY_USER)
            query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
            query.addDescendingOrder("createdAt")
            query.findInBackground(object : FindCallback<Post> {
                override fun done(posts: MutableList<Post>?, e: ParseException?) {
                    if (e != null) {
                        Log.e(TAG, "Error Fetching posts")
                    } else {
                        if (posts != null) {
                            for (post in posts) {
                                Log.i(TAG, "Post:" + post.getDescription() + ", username: "+
                                        post.getUser()?. username)
                            }
                        }
                        if (posts != null) {
                            allPosts.addAll(posts)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            })


    }

}