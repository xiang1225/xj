package cn.edu.nenu.service;

import cn.edu.nenu.domain.Storage;
import cn.edu.nenu.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * StorageService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-28 13:57
 */
@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepo;

    public Page<Storage> getPage(int pageNumber, int pageSize, String description) {
        /**
         * 两个参数：当前页面（需要-1转换成数据库的索引页面），每页记录数
         * 数据库分页     页面显示分页
         *   0             1
         *   1             2
         *   2             3
         */
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);//构建分页请求条件类
        /**
         * 标准的语句
         */
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("description"),description));
                cb.and(predicates.toArray(new Predicate[predicates.size()]));

                return cb.conjunction();
            }
        };
        /**
         * JDK Lambda语句
         */
        Specification spec1 = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("description"),description));
            cb.and(predicates.toArray(new Predicate[predicates.size()]));
            return cb.conjunction();
        };
        /**
         * JDK Lambda语句
         */
        Specification spec2 = (root, query, cb) -> null;

        /**
         * findAll请求参数说明：
         *  - spec: 查询条件
         *  - pageRequest：spring自带的，与分页相关的参数：当前页，每页的记录数，<总页数，总记录数>
         */
        return storageRepo.findAll(spec,pageRequest);
    }

    /**
     * 保存
     * @param entity
     * @return
     */
    public Storage save(Storage entity){
        return storageRepo.save(entity);
    }

    /**
     * 根据主键获取实体信息
     * @param pkId
     * @return
     */
    public Storage findOne(Integer pkId){
        return storageRepo.findOne(pkId);
    }
}
