package cn.edu.nenu.service;

import cn.edu.nenu.config.CommonUtil;
import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.Organization;
import cn.edu.nenu.repository.OrganizationRepository;
import cn.edu.nenu.repository.mybatis.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrganizationService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 16:13
 */
@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepo; //JPA
    //final】
    @Autowired
    OrganizationMapper organizationMapper;  //Mybatis

    /**
     * 保存
     */
    public Organization save(Organization entity){
        return organizationRepo.save(entity);
    }
    //
    ///**
    // * 更新
    // */
    //public void update(Organization entity){
    //    organizationRepo.save(entity);
    //}
    //
    ///**
    // * 删除
    // */
    public void delete(Integer id){
        organizationRepo.delete(id);
    }
    public void delete(String autoCode){
        organizationMapper.delete(autoCode);
    }
    //
    /**
     * 获取机构信息
     * @return
     */
    public Organization get(Integer id){
        return organizationRepo.findOne(id);
    }
    public Organization get(String autoCode){
        return organizationRepo.findByAutoCode(autoCode);
    }
    ///**
    // * 上移，改变的是排序规则，其他不改变
    // * @return
    // */
    //public String moveUp(Integer id){
    //    return null;
    //}
    //
    ///**
    // * 下移，改变的是排序规则，其他不改变
    // * @return
    // */
    //public String moveDown(Integer id){
    //    return null;
    //}
    //
    /**
     * 获取子节点
     * @return
     */
    public List getSubList(Integer id){
        return organizationRepo.findByPId(id);
    }
    public List getSubList(String autoCode){
        return null;
    }

    /**
     * 获取本级最大四位一体编码
     * @param autoCode
     * @return
     */
    public String getMaxAutoCode(String autoCode){
        //截取掉后四位，获得上级编码，调用getSubMaxAutoCode获得本级最大编码
        return "0002";
    }
    //
    /**
     * 获取下级最大四位一体编码
     * @param autoCode
     * @return
     */
    public String getSubMaxAutoCode(String autoCode){
        // 0001-> 模糊查询0001____->将____4位截取出来转换为数字->在转换的数字中找最大值->最大值+1->格式化位4位编码XXXX->0001XXXX
        return null;
    }
    public String getSubMaxAutoCode(Integer pid){
        //返回值是当前最大值+1格式化之后的值，000100040003->0003->3+1->4->0004->000100040004
        Sort sort = new Sort(Sort.Direction.DESC, "autoCode");

        SearchFilter filter = new SearchFilter("pId", SearchFilter.Operator.EQ,pid);
        Specification<Organization> spec = DynamicSpecifications.byOne(filter,Organization.class);

        PageRequest pageRequest = new PageRequest(0, 1, sort);
        Page<Organization> pages = organizationRepo.findAll(spec,pageRequest);
        List pageList = pages.getContent();
        String autoCode ="";
        if (pageList==null || pageList.size()==0){
            Organization organization = organizationRepo.getOne(pid);
            autoCode = organization.getAutoCode()+"0001";
        }else {
            Organization organization = (Organization) pageList.get(0);
            autoCode = CommonUtil.getNextAutoCode(organization.getAutoCode(),4);
    }

        //List<Organization> organizationList = organizationRepo.findAll(spec, sort);
        //System.out.println(organizationList.get(0).getId());
        //String autoCode = organizationList.get(0).getAutoCode();
        //Organization organization = organizationRepo.findOne(pid);
        //String autoCode = organization.getAutoCode();
        //System.out.println("autoCode:"+autoCode);
        return autoCode;
    }
    //
    /**
     * 获取本级排序最大值
     * @return
     */
    public float getMaxSort(Integer id){
        //根据id获取pID，调用getSubMaxSort获取最大值
        Organization organization = organizationRepo.findOne(id);
        return getSubMaxSort(organization.getPId());
    }

    /**
     * 获取下级排序最大值
     * @param pId
     * @return
     */
    public float getSubMaxSort(Integer pId){
        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        // 根据pId获取子节点最大的autoCode
        SearchFilter filter = new SearchFilter("pId", SearchFilter.Operator.EQ,pId);
        Specification<Organization> spec = DynamicSpecifications.byOne(filter,Organization.class);
        PageRequest pageRequest = new PageRequest(0, 1, sort);
        Page<Organization> pageList = organizationRepo.findAll(spec,pageRequest);
        Float maxSort =0f;
        if (pageList.getContent()!=null && pageList.getContent().size()>0 ){
            maxSort = pageList.getContent().get(0).getSort();
        }
        //if (maxSort == null){
        //    maxSort=0f;
        //}
        return maxSort+1f;
    }

    public float getSubMaxSortByMapper(Integer pId){
        Float maxSort = organizationMapper.getSubMaxSort(pId);
        if (maxSort==null){
            maxSort=0f;
        }
        return maxSort+1f;
    }
}
