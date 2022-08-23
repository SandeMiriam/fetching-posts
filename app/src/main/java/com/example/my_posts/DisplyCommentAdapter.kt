package com.example.my_posts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_posts.databinding.PostListItemBinding

class DisplyCommentAdaptery(var displayComment: List<Post>) :
    RecyclerView.Adapter<CommentViewAdapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewAdapter {
        var binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewAdapter(binding)
    }

    override fun onBindViewHolder(holder: CommentViewAdapter, position: Int) {
        var currentComment = displayComment.get(position)
        with(holder.binding) {
            tvUserId.text = currentComment.userId.toString()
            tvId.text = currentComment.id.toString()
            tvTitle.text = currentComment.title
            tvBody.text = currentComment.body

            cvComms.setOnClickListener {
                var context = holder.itemView.context
                val intent = Intent(context, Comment_Activity::class.java)
                intent.putExtra("POST_ID", currentComment.id)

                context.startActivity(intent)

            }
        }
    }

    override fun getItemCount(): Int {
        return displayComment.size
    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitviewHolder {
//        var binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return RetrofitviewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: RetrofitviewHolder, position: Int) {
//        var currentComment =displayComment.get(position)
//        with(holder.binding){
//            tvUserId.text = currentComment.userId.toString()
//            tvId.text = currentComment.id.toString()
//            tvTitle.text = currentComment.title
//            tvBody.text = currentComment.body

//            cvComms.setOnClickListener {
//                var context= holder.itemView.context
//                val intent  = Intent(context,Comment_Activity::class.java)
//                intent.putExtra("POST_ID", currentComment.id)

//                context.startActivity(intent)

//        }
//    }
//    override fun getItemCount(): Int {
//        return displayComment.size
//    }
}
class CommentViewAdapter(val binding: PostListItemBinding):
    RecyclerView.ViewHolder(binding.root){

}
