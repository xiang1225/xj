package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dictionary Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-16 14:09
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_DICTIONARY")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 128,nullable = false)
    private String name; //字典类型
    @Column(nullable = false)
    private float sort; //属性名称
    @Column(nullable = false)
    private Integer pId;//属性值
    @Column(length=240,unique = true)
    private String autoCode;//属性状态
}
