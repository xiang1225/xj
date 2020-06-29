package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Organization Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 15:07
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_ORGANIZATION")
public class Organization {

    @Id
    @NotFound(action= NotFoundAction.IGNORE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键编码
    @Column(length = 128,nullable = false)
    private String name;//名称
    @Column(nullable = false)
    private float sort;//排序
    @Column(nullable = false)
    private Integer pId;//父编码
    @Column(length = 240,unique = true)
    private String autoCode;//四位一体编码(60层)
}

/*****
 *  下节课的内容概要
 *  一。开发实现Organization功能
 *  1.开发如何实现organization功能
 *  2.父子ID，四位一体
 *  3.controller，service，repository，JPA，Mybatis
 *  4.树，表格
 *
 *  二。将剩余需求实体全部完成
 *  角色、权限、文章栏目、文章内容
 *
 *
 */