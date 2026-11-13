package cn.edu.sicnu.cs.fangc.fangchen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 第 2 个 Activity：显示被选中书籍的完整信息
 */
class BookDetailActivity : AppCompatActivity() {

    companion object {
        /** 接收书籍对象的 Key */
        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_detail)
        applyWindowInsets()

        // 取出第 1 个 Activity 传过来的书籍对象
        @Suppress("DEPRECATION")
        val book = intent.getSerializableExtra(EXTRA_BOOK) as? Book
        if (book == null) {
            finish()
            return
        }

        // 显示完整信息
        findViewById<TextView>(R.id.tvDetailTitle).text = book.title
        findViewById<TextView>(R.id.tvDetailAuthor).text = book.author
        findViewById<TextView>(R.id.tvDetailPublisher).text = book.publisher
        findViewById<TextView>(R.id.tvDetailYear).text = getString(R.string.year_format, book.year)
        findViewById<TextView>(R.id.tvDetailPrice).text = getString(R.string.price_format, book.price)
        findViewById<TextView>(R.id.tvDetailSummary).text = book.summary

        // 左上角返回按钮
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
    }

    /** 处理边到边显示，避免内容被状态栏、导航栏遮挡 */
    private fun applyWindowInsets() {
        val root = findViewById<View>(R.id.detailRoot)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }
}
