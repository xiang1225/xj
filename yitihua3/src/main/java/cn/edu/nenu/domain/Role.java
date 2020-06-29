package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Role Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 15:40
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键ID
    private String name;//名称
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "T_ROLE_PERM_CORR",
            joinColumns = {@JoinColumn(name = "role_id",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "perm_id",nullable = false)}
    )
    @OrderBy("id asc")//排序策略
    private Set<Permission> permissions;
    private float sort;//排序
    private Integer status;//状态
}
