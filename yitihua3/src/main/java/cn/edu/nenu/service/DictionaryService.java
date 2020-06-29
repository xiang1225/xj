package cn.edu.nenu.service;

import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * DictionaryService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:15
 */
@Service
public class DictionaryService {


    @Autowired
    DictionaryRepository dictRepo;

    /**
     * 根据主键获取实体，常用
     */
    public Dictionary findOne(Long pkId){
        return dictRepo.findOne(pkId);
    }

    /**
     * 保存一个实体，常用
     */
    public Dictionary save(Dictionary entity){
        return dictRepo.save(entity);
    }

    /**
     * 批量保存实体  Set，List
     */
    public Iterable<Dictionary> sava(Iterable<Dictionary> entities){
        return dictRepo.save(entities);
    }

    /**
     * 根据主键删除实体，常用
     */
    public void remove(Long pkId){
        dictRepo.delete(pkId);
    }

    /**
     * 根据实体删除实体，不常用
     */
    public void remove(Dictionary entity){
        dictRepo.delete(entity);
    }

    /**
     * 批量删除实体，使用较少
     */
    public void remove(Iterable<Dictionary> dicts){
        dictRepo.delete(dicts);
    }

    /**
     * 根据字典类型获取字典集合
     */
    public List<Dictionary> findByType(String type){
        return dictRepo.findByTypeOrderBySort(type);
    }

    /**
     * 根据查询条件获取分页结果数据
     */
    public Page<Dictionary> getEntityPage(Map<String, Object> filterParams, int pageNumber, int pageSize, String sortType){
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<Dictionary> spec = buildSpecification(filterParams);
        return dictRepo.findAll(spec,pageRequest);
    }
    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "sort");
        } else if ("sort".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "sort");
        }
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<Dictionary> buildSpecification(Map<String, Object> filterParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
        //if (StringUtils.isNotBlank(id)) {
        //    filters.put("id", new SearchFilter("id", SearchFilter.Operator.EQ, id));
        //}
        Specification<Dictionary> spec = DynamicSpecifications.bySearchFilter(filters.values(), Dictionary.class);
        return spec;
    }

    public float getMaxSort() {
        Dictionary dict = dictRepo.findFirstByOrderBySortDesc();
        if (dict==null){
            return 0;
        }else {
            return dict.getSort();
        }

    }
}
