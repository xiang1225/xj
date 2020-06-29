package cn.edu.nenu.repository;

import cn.edu.nenu.config.orm.PlatformRepository;
import cn.edu.nenu.domain.Organization;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * OrganizationRepository Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-07 11:12
 */
public interface OrganizationRepository extends PlatformRepository<Organization,Integer>{
        //JpaRepository<Organization,Integer>, JpaSpecificationExecutor<Organization> {

    @Query("from Organization o where o.autoCode=?1")
    Organization findByAutoCode(String autoCode);

    @Query("from Organization o where o.pId=?1")
    List findByPId(Integer id);
}
