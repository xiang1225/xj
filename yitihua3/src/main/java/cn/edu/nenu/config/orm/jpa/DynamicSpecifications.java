package cn.edu.nenu.config.orm.jpa;

import cn.edu.nenu.config.CollectionsUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

/**
 * DynamicSpecifications Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2019-05-09 22:31
 */
public class DynamicSpecifications {

    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (CollectionsUtil.isNotEmpty(filters)) {

                    List<Predicate> predicates = Lists.newArrayList();
                    for (SearchFilter filter : filters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // logic operator
                        switch (filter.operator) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.value));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + filter.value + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                        }
                    }

                    // 将所有条件用 and 联合起来
                    if (!predicates.isEmpty()) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }
        };
    }

    public static <T> Specification<T> byOne(final SearchFilter filter,final Class<T> clazz){
        return (root, query, builder) -> {

            Predicate[] predicate = new  Predicate[1];

            String[] names = StringUtils.split(filter.fieldName, ".");
            Path expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }

            switch (filter.operator) {
                case EQ:
                    predicate[0]=builder.equal(expression, filter.value);
                    break;
                case LIKE:
                    predicate[0]=builder.like(expression, "%" + filter.value + "%");
                    break;
                case GT:
                    predicate[0]=builder.greaterThan(expression, (Comparable) filter.value);
                    break;
                case LT:
                    predicate[0]=builder.lessThan(expression, (Comparable) filter.value);
                    break;
                case GTE:
                    predicate[0]=builder.greaterThanOrEqualTo(expression, (Comparable) filter.value);
                    break;
                case LTE:
                    predicate[0]=builder.lessThanOrEqualTo(expression, (Comparable) filter.value);
                    break;
            }
            return predicate[0];
        };
    }
}
