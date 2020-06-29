package cn.edu.nenu.service;

import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    UserRepository userRepo;

    /**
     * 根据主键获取实体，常用
     */
    public User findOne(Long pkId){
        return userRepo.findOne(pkId);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
    /**
     * 保存一个实体，常用
     */
    public User save(User entity){
        return userRepo.save(entity);
    }

    /**
     * 批量保存实体  Set，List
     */
    public Iterable<User> sava(Iterable<User> entities){
        return userRepo.save(entities);
    }

    /**
     * 根据主键删除实体，常用
     */
    public void remove(Long pkId){
        userRepo.delete(pkId);
    }

    /**
     * 根据实体删除实体，不常用
     */
    public void remove(User entity){
        userRepo.delete(entity);
    }

    /**
     * 批量删除实体，使用较少
     */
    public void remove(Iterable<User> users){
        userRepo.delete(users);
    }

//    /**
//     * 根据字典类型获取字典集合
//     */
//    public List<User> findByType(String type){
//        return userRepo.findByTypeOrderBySort(type);
//    }

    /**
     * 根据查询条件获取分页结果数据
     */
    public Page<User> getEntityPage(Map<String, Object> filterParams, int pageNumber, int pageSize){
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Specification<User> spec = buildSpecification(filterParams);
        return userRepo.findAll(spec,pageRequest);
    }
    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize) {
//        Sort sort = null;
//        if ("auto".equals(sortType)) {
//            sort = new Sort(Sort.User.ASC, "sort");
//        } else if ("sort".equals(sortType)) {
//            sort = new Sort(Sort.Direction.ASC, "sort");
//        }
        return new PageRequest(pageNumber - 1, pageSize);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<User> buildSpecification(Map<String, Object> filterParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
        //if (StringUtils.isNotBlank(id)) {
        //    filters.put("id", new SearchFilter("id", SearchFilter.Operator.EQ, id));
        //}
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return spec;
    }

//    public float getMaxSort() {
//        User User = userRepo.findFirstByOrderBySortDesc();
//        if (User==null){
//            return 0;
//        }else {
//            return User.getSort();
//        }
//
//    }
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






















