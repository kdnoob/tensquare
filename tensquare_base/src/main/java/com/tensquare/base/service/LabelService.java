package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 条件查询 - 不分页  
     *  @param controller  
     *  @return  List<Label>
     */
    public List<Label> findSearch(Label label) {
        Specification<Label> specification = createSpecification(label);
        return labelDao.findAll(specification);
    }

    /**
     * 查询 -》 条件 + 分页
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Specification<Label> specification = createSpecification(label);
        return labelDao.findAll(specification, pageable);
    }

    /**
     * 构建查询条件
     *
     * @param label
     * @return
     */
    private Specification<Label> createSpecification(Label label) {
        return new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中，where 类名 = controller.getid
             * @param criteriaQuery 封装的都是查询关键字，比如 group by order by 等
             * @param cb 用来封装条件对象的，如果直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    // where labelname like "%xx%"
                    predicateList.add(cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    predicateList.add(cb.equal(root.get("state").as(String.class), (String) label.getState()));
                }
                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    predicateList.add(cb.equal(root.get("recommend").as(String.class), (String) label.getRecommend()));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
