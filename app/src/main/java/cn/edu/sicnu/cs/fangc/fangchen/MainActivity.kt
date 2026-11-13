package cn.edu.sicnu.cs.fangc.fangchen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 第 1 个 Activity：书籍列表
 *
 * 1. 在代码中创建多个书籍对象，放入数组；
 * 2. 按价格从低到高排序；
 * 3. 使用 RecyclerView 列表显示；
 * 4. 单击某一项启动第 2 个 Activity，并把选中的书籍传过去。
 */
class MainActivity : AppCompatActivity() {

    /** 书籍数组（在代码里直接创建对象） */
    private lateinit var books: Array<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        applyWindowInsets()

        // 1. 在代码中新建多个书籍对象并放入数组
        books = createBooks()

        // 2. 对数组里的书籍按价格从低到高排序（原地排序）
        books.sortWith(compareBy { it.price })

        // 统计信息（顺带说明列表已排序）
        findViewById<TextView>(R.id.tvSubtitle).text =
            getString(R.string.list_subtitle, books.size)

        // 3. 采用列表显示这些书籍
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerBooks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. 单击某一本书籍，启动第 2 个 Activity 并传递该书籍对象
        recyclerView.adapter = BookAdapter(books.toList()) { book ->
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_BOOK, book)
            startActivity(intent)
        }
    }

    /**
     * 创建书籍数组。价格故意打乱顺序，用于验证排序效果。
     */
    private fun createBooks(): Array<Book> = arrayOf(
        Book(
            title = "三体",
            author = "刘慈欣",
            publisher = "重庆出版社",
            price = 23.00,
            year = 2008,
            summary = "军方探寻外星文明的绝秘计划“红岸工程”取得突破进展，地球文明向宇宙发出的一声啼鸣，彻底改变了人类的命运。"
        ),
        Book(
            title = "活着",
            author = "余华",
            publisher = "作家出版社",
            price = 20.00,
            year = 2012,
            summary = "讲述一个人和他命运之间的友情，讲述人如何去承受巨大的苦难，也讲述眼泪的宽广和丰富。"
        ),
        Book(
            title = "百年孤独",
            author = "加西亚·马尔克斯",
            publisher = "南海出版公司",
            price = 39.50,
            year = 2011,
            summary = "布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰。"
        ),
        Book(
            title = "平凡的世界",
            author = "路遥",
            publisher = "北京十月文艺出版社",
            price = 68.00,
            year = 2017,
            summary = "以中国 70 年代中期到 80 年代中期为背景，刻画普通人在大时代历史进程中所走过的艰难曲折的道路。"
        ),
        Book(
            title = "解忧杂货店",
            author = "东野圭吾",
            publisher = "南海出版公司",
            price = 28.60,
            year = 2014,
            summary = "僻静街道旁的一间杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。"
        ),
        Book(
            title = "人类简史",
            author = "尤瓦尔·赫拉利",
            publisher = "中信出版社",
            price = 54.00,
            year = 2014,
            summary = "从认知革命、农业革命到科学革命，重新审视人类如何一步步走上食物链的顶端。"
        )
    )

    /** 处理边到边显示，避免内容被状态栏、导航栏遮挡 */
    private fun applyWindowInsets() {
        val root = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }
}
