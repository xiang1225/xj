package cn.edu.nenu.repository.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * OrganizationMapper Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-07 11:13
 */
//@Repository
//@Mapper
public interface OrganizationMapper {

    @Update("UPDATE T_ORGANIZATION SET name=#{name} where id=#{id}")
    int update(@Param("id") Integer id,@Param("name") String name);

    @Delete("DELETE FROM T_ORGANIZATION WHERE auto_code = #{autoCode}")
    int delete(@Param("autoCode") String autoCode);

    @Select("SELECT o.sort from T_ORGANIZATION o WHERE o.p_id=#{pId} order by sort desc limit 1")
    Float getSubMaxSort(Integer pId);
}
