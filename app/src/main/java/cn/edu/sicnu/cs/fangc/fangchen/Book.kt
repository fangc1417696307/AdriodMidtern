package cn.edu.sicnu.cs.fangc.fangchen

import java.io.Serializable

/**
 * 书籍实体类
 *
 * 属性：书名（title）、作者（author）、出版社（publisher）、价格（price），
 * 以及出版年份（year）、内容简介（summary）等扩展信息。
 *
 * 实现 Serializable 接口，便于通过 Intent 在两个 Activity 之间传递书籍对象。
 */
data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val price: Double,
    val year: Int,
    val summary: String
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}
