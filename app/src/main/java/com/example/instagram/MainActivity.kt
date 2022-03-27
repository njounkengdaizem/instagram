package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

/**
 *  Let user create a post by taking a photo with their camera
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queryPosts()
    }

    //Query for all post in our server
    fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all post objects
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error Fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post:" + post.getDescription())
                        }
                    }
                }
            }
        })
    }
    companion object {
        const val TAG = "MainActivity"
    }
}