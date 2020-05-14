package cn.edu.nenu.repository;

import cn.edu.nenu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 23:04
 */
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    //@Query("select u from User u where u.id=?1") // JPQL查询语言
    @Query("from User u where u.id=?1") //与上面写法是等效的，或直接注释掉均可
    User findById(Long pkId);


    /**
     * 三个方法是等效的
     */

    //方法一
    //User findById(Long pkId);

    //方法二
    //@Query("from User u where u.id=?1") //与上面写法是等效的，或直接注释掉均可
    //User findById(Long pkId);

    //方法三
    //@Query("select u from User u where u.id=?1") // JPQL查询语言
    //User findById(Long pkId);


    /**
     * 关系链
     *
     *    全功能         分页、排序                       增删改查           接口
     * JpaRepository-> PagingAndSortingRepository -> CrudRepository -> Repository
     *             |-> QueryByExampleExecutor
     *
     * JpaSpecificationExecutor 特殊查询
     */


    /**
     * 执行顺序
     *
     * 正向的顺序：
     * controller.get -> service.findById -> repository.findById
     *
     *  controller: 负责接收参数、返回响应数据
     *
     *  service： 业务处理、计算
     *
     *  repository： 数据的操作，将db->java pojo, java pojo ->db
     *
     *  domain: 数据库table表与Java的Entity属性对应关系映射
     *
     *
     */







}
