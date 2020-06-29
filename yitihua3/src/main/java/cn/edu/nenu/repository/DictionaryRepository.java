package cn.edu.nenu.repository;

import cn.edu.nenu.config.orm.PlatformRepository;
import cn.edu.nenu.domain.Dictionary;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * DictionaryRepository Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:17
 */
public interface DictionaryRepository extends PlatformRepository<Dictionary,Long> {


    @Query("from Dictionary d where d.type=?1 order by d.sort asc ")
    List<Dictionary> findByTypeOrderBySort(String type);

    Dictionary findFirstByOrderBySortDesc();
}
