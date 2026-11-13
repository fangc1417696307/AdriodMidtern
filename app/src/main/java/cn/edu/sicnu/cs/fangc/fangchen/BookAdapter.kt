package cn.edu.sicnu.cs.fangc.fangchen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * 书籍列表适配器（RecyclerView.Adapter）
 *
 * @param books       已按价格升序排好序的书籍列表
 * @param onBookClick 列表项的点击回调，用于启动第 2 个 Activity
 */
class BookAdapter(
    private val books: List<Book>,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position], position)
    }

    override fun getItemCount(): Int = books.size

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvIndex: TextView = itemView.findViewById(R.id.tvIndex)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        fun bind(book: Book, position: Int) {
            val context = itemView.context
            tvIndex.text = (position + 1).toString()
            tvTitle.text = book.title
            tvAuthor.text = context.getString(R.string.list_item_author, book.author, book.publisher)
            tvPrice.text = context.getString(R.string.price_format, book.price)

            // 单击某一本书籍 -> 回调给 Activity 处理（启动第 2 个 Activity）
            itemView.setOnClickListener { onBookClick(book) }
        }
    }
}
