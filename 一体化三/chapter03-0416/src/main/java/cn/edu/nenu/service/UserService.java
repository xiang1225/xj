package cn.edu.nenu.service;

import cn.edu.nenu.domain.User;
import cn.edu.nenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * UserService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:54
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserMapper userMapper;

    /**
     * 根据主键ID获取实体对象
     * @param pkId
     * @return
     */
    public User findById(Long pkId){
        return userRepository.findById(pkId);
    }

    /**
     * 当前页面数据（当前页码，每页的记录数，查询参数）
     * @param pageNumber
     * @param pageSize
     * @param param
     * @return
     */
    public Page<User> getPage(int pageNumber, int pageSize, Map<String,Object> param) {

        /*******************************************************************************
         * 数据访问层接口不同有两种实现方式
         *
         * 第一种：利用spring Data JPA 进行查询分页
         *
         */
        // 单表查询，多表查询无能为力
        //参数查询的构建
        Specification<User> spec= new Specification<User>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String,Object> entry:param.entrySet()){
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.equals("username")){
                        Path username = root.get("username");
                        predicates.add(cb.equal(username,value));//相等
                    }else if(key.equals("age")){
                        Path age = root.get("age");
                        predicates.add(cb.greaterThan(age,(Comparable)value));//大于
                    }
                }

                //Path username = root.get("username");
                //predicates.add(cb.equal(username,"admin"));//相等
                //Path age = root.get("age");
                //predicates.add(cb.greaterThan(age,28));//大于
                //
                //cb.and(cb.equal(username,"admin"),cb.greaterThan(age,28));
                ////"username=admin and age>=28";
                //cb.or(cb.equal(username,"admin"),cb.greaterThan(age,28));
                // "username=admin or age>=28"
                //predicates.add(cb.equal(expression,"admin"));//相等
                cb.and(predicates.toArray(new Predicate[predicates.size()]));
                /**
                 * CriteriaBuilder的表达式关键词
                 * 并且：and
                 * 或者：or
                 * 等于：equal
                 * 相似：like
                 * 大于：greaterThan
                 * 小于：lessThan
                 * 大于等于：greaterThanOrEqualTo
                 * 小于等于：lessThanOrEqualTo
                 */
                return cb.conjunction();
            }
        };

        Sort sort = new Sort(Sort.Direction.ASC,"username");
        PageRequest pageRequest = new PageRequest(pageNumber-1,pageSize, sort); //索引值=页码值-1
        Page pageJPA = userRepository.findAll(spec,pageRequest);

        /*********************************************************************************
         * 第二种：利用MyBatis SQL语句进行查询分页
         *
         **********************************************************************************/

        //Mybatis, SQL语句查询
        //List<User> list = userMapper.getPage(pageNumber,pageSize,param);
        //Pageable pageable = new Pageable() {
        //    @Override
        //    public int getPageNumber() {
        //        return pageNumber;
        //    }
        //
        //    @Override
        //    public int getPageSize() {
        //        return pageSize;
        //    }
        //
        //    @Override
        //    public int getOffset() {
        //        return 0;
        //    }
        //
        //    @Override
        //    public Sort getSort() {
        //        return null;
        //    }
        //
        //    @Override
        //    public Pageable next() {
        //        return null;
        //    }
        //
        //    @Override
        //    public Pageable previousOrFirst() {
        //        return null;
        //    }
        //
        //    @Override
        //    public Pageable first() {
        //        return null;
        //    }
        //
        //    @Override
        //    public boolean hasPrevious() {
        //        return false;
        //    }
        //};
        //Page page = new PageImpl(list,pageable,list.size());
        return pageJPA;
    }

    /**
     * 持久化实体类
     * @param entity
     * @return
     */
    public User save(User entity) {

        /**
         * 使用了接口类，通用类中使用了泛型
         */
        return userRepository.save(entity);
    }

    /**
     * 批量持久化
     * @param entities
     * @return
     */
    public Collection save(Collection entities){
        return userRepository.save(entities);
    }
    public void delete(Long pkId){
        userRepository.delete(pkId);
    }
    public void delete(User entity){
        userRepository.delete(entity);
    }
}


/**
 *
 *
 * Spring MVC + Spring + JPA(Hibernate)
 *
 * Spring MVC + Spring + MyBatis
 *
 * Spring MVC + Spring + JPA(基本操作) + MyBatis（涉及性能或复杂业务处理）
 *
 * JSP、vue、React、H5（Thymeleaf）
 *
 * 前后端分离开发
 *   - Spring MVC @RestController，request and response JSON
 *   - 异步请求，Ajax，axios，
 *
 *
 *
 *   Spring Data JPA
 *   好处：仿照JPA接口的实现Mybatis进行自定义，save(collection entities)
 *
 *
 */






















