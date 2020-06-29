package cn.edu.nenu.service;

import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.Category;
import cn.edu.nenu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Swingland on 2020/6/24 15:40
 * Tomorrow is a new day!
 */
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    /**
     * 根据主键获取实体，常用
     */
    public Category findOne(Integer pkId){
        return categoryRepo.findOne(pkId);
    }

    /**
     * 保存一个实体，常用
     */
    public Category save(Category entity){
        return categoryRepo.save(entity);
    }

    /**
     * 批量保存实体  Set，List
     */
    public Iterable<Category> sava(Iterable<Category> entities){
        return categoryRepo.save(entities);
    }

    /**
     * 根据主键删除实体，常用
     */
    public void remove(Integer pkId){
        categoryRepo.delete(pkId);
    }

    /**
     * 根据实体删除实体，不常用
     */
    public void remove(Category entity){
        categoryRepo.delete(entity);
    }

    /**
     * 批量删除实体，使用较少
     */
    public void remove(Iterable<Category> users){
        categoryRepo.delete(users);
    }

//    /**
//     * 根据字典类型获取字典集合
//     */
//    public List<CategoryRepository> findByType(String type){
//        return categoryRepo.findByTypeOrderBySort(type);
//    }

    /**
     * 根据查询条件获取分页结果数据
     */
    public Page<Category> getEntityPage(Map<String, Object> filterParams, int pageNumber, int pageSize){
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Specification<Category> spec = buildSpecification(filterParams);
        return categoryRepo.findAll(spec,pageRequest);
    }
    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize) {
//        Sort sort = null;
//        if ("auto".equals(sortType)) {
//            sort = new Sort(Sort.CategoryRepository.ASC, "sort");
//        } else if ("sort".equals(sortType)) {
//            sort = new Sort(Sort.Direction.ASC, "sort");
//        }
        return new PageRequest(pageNumber - 1, pageSize);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<Category> buildSpecification(Map<String, Object> filterParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
        //if (StringUtils.isNotBlank(id)) {
        //    filters.put("id", new SearchFilter("id", SearchFilter.Operator.EQ, id));
        //}
        Specification<Category> spec = DynamicSpecifications.bySearchFilter(filters.values(), Category.class);
        return spec;
    }
}
