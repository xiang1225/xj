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
 * Storage Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-28 13:49
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_STORAGE")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128,nullable = false)
    private String path;//路径
    @Column(length = 254)
    private String description;//描述
    @Column(length = 32)
    private String type; //存储类型(image,word,excel,pdf,zip等，按年份月份存储)
}
