package com.example.my_posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_posts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Comment_Activity : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostId()
        getComment()
//        displayComment()
//        setupToolBar()


    }

    fun obtainPostId() {
        postId = intent.extras?.getInt("POST_ID") ?: 0

    }

    fun fetchPostId() {
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPostsById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    if (post != null) {
                        binding.tvPostbody.text = post.body
                    }
                    if (post != null) {
                        binding.tvPosttitle.text = post.title
                    }
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    fun getComment() {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val request = apiClient.getComment(postId)
    request.enqueue(object : Callback<List<Comment>> {
        override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
            if (response.isSuccessful) {
                var comment = response.body()
                if (comment != null) {
                    displayComment(comment)
                }
            }
        }

        override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
            Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
        }
    })
}

    fun  displayComment(displayComment:List<Comment>){
        val  adapter =DisplyCommentAdaptery(displayComment)
        binding.rvdisplycomment.layoutManager=LinearLayoutManager(this)
        binding.rvdisplycomment.adapter=adapter
    }
}

//private fun Any.enqueue(callback: Callback<List<Comment>>) {
//
//}

private fun <T> Call<T>.enqueue(callback: Callback<List<Comment>>) {

}
