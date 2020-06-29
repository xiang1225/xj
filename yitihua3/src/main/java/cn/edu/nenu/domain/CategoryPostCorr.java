package cn.edu.nenu.domain;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * CategoryPostCorr Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-07 11:04
 */
//@Data
//@Accessors(chain = true)
//@Entity
//@Table(name = "T_CATEGORY_POST_CORR")
public class CategoryPostCorr {

    @Id
    private String id;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Post post;
}
