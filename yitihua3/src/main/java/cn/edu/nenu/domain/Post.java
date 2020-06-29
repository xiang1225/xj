package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Post Class
 *  文章内容管理
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 15:46
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_POST")
public class Post {

    /**
     * 主键生成原则：
     *  - Integer, Long:数据库自动增长生成主键ID
     *  - String: 手动生成主键ID，进行复制，同时需保障主键ID的唯一性；
     *  比较著名的ID生成算法：
     *      - 基于UUID生成
     *      - twitter的snowflake;俗称雪花算法
     *      - baidu的uid-generator:百度优化雪花算法得来的
     *      - 美团的Leaf
     *      - 利用数据库生成ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键编码  Long,String;对文章的总数进行预估，一般要预留出3-5年的数据量变化情况
    @Column(length = 254)
    private String title;//文章标题
    /**
     * 类型：
     *  1-字符串
     *  2-二进制流
     */
//    @Lob
//    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "text")
    private String content;//文章内容

    /**
     * 三种写法：
     *  1.在Entity中直接增加Set集合方式（使用JPA作为ORM框架，推荐使用此方式）
     *  2.创建一个对应关系类Corr，此类使用联合主键方式（Mybatis推荐使用此方式）
     *  3.创建一个对应关系类Corr，使用独立的字符型ID作为主键，此时无法进行唯一性校验，只能通过程序判断。
     */
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "T_POST_CATEGORY_CORR",
//            joinColumns = {@JoinColumn(name = "post_id",nullable = false)},
//            inverseJoinColumns ={@JoinColumn(name = "category_id",nullable = false)}
//    )
//    @OrderBy("id asc")//排序策略
    @Column(length = 254)
    private Integer categories;//针对一个文章不能重复分类，对应关系保持唯一的。
    private String creator;//创建者
    private LocalDateTime createdAt;//发布时间
    private LocalDateTime lastModifiedAt;//最后编辑时间
    private Integer status;//状态
}


