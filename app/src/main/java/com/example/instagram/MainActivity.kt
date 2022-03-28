package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

/**
 *  Let user create a post by taking a photo with their camera
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Setting the description of the post
        //2. A button to launch the camera to take the picture
        //3. An image view to show the picture
        //4. A button to save and send the post

        findViewById<Button>(R.id.btnSubmit).setOnClickListener{
            //Send post to server without an image

        findViewById<Button>(R.id.btnTakePicture).setOnClickListener {
            //launch camera to let user take picture
            //grab the description inputed

            val description = findViewById<EditText>(R.id.description).text.toString()
            val user = ParseUser.getCurrentUser()
            submitPost(description, user)
        }
                findViewById<Button>(R.id.btnTakePicture).setOnClickListener{

            }

        }
        queryPosts()
    }
    fun submitPost(description: String, user:ParseUser){
        val post = Post()
        post.setDecription(description)
        post.setUser(user)
        post.saveInBackground{exception->
            if (exception!=null){
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
                //TODO: Show a toast
            } else{
                Log.i(TAG, "Successfully saved post")
                //TODO: Resetting edit text view to empty
                //TODO: Reset imageview to empty
            }
        }

    }

    //Query for all post in our server
    fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all post objects
        query.include(Post.KEY_USER)
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
                }
            }
        })
    }
    companion object {
        const val TAG = "MainActivity"
    }
}