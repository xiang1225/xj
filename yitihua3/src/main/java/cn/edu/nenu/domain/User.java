package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:48
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "ssh_user") //数据库表名
public class User implements Serializable {


    /**
     * //TODO：作业（4）-1 GenerationType的四种常量，有何区别，均代表什么含义
     */
    @Id //主键ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 生成主键策略
    private long id; //

    // 列名、长度32个字符、是否为空（false否，不允许为空）、是否唯一（true唯一）
    @Column(name = "user_name",length = 32,nullable = false,unique = true)
    private String username; //用户名
    private String password; //密码
    //@Transient //不持久化此字段，这个字段在数据库中不存在
    private String name; //姓名
    private String sex; //性别
    private Integer status;//状态
    //@Temporal(value = TemporalType.DATE )
    private LocalDateTime createdAt;//创建时间 created_at
}
