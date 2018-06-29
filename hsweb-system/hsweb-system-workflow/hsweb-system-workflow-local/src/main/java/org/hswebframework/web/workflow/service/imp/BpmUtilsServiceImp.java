package org.hswebframework.web.workflow.service.imp;

import org.hswebframework.web.entity.organizational.PersonEntity;
import org.hswebframework.web.organizational.authorization.relation.Relation;
import org.hswebframework.web.service.organizational.PersonService;
import org.hswebframework.web.service.organizational.RelationInfoService;
import org.hswebframework.web.workflow.service.BpmUtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2017/9/13.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BpmUtilsServiceImp implements BpmUtilsService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    PersonService personService;
    @Autowired(required = false)
    RelationInfoService relationInfoService;

    public void assertOrgModuleReady(){
        Assert.notNull(personService,"未引入组织架构模块依赖");
        Assert.notNull(relationInfoService,"未引入组织架构模块依赖");

    }

//    @Override
//    public List<String> selectUserIdsBy(String userId, ActDefEntity actDefEntity) {
//        assertOrgModuleReady();
//        List<String> list = new ArrayList<>();
//        // 根据配置类型  获取人员信息 设置待办人
//        if ("person".equals(actDefEntity.getDimension())) { // 矩阵
//            List<Relation> relations = relationInfoService.getRelations(actDefEntity.getDimension(), userId).findPos(actDefEntity.getDefId());
//            for (Relation relation : relations) {
//                list.add(relation.getTarget());
//            }
//        } else if ("position".equals(actDefEntity.getDimension())) { // 岗位
//            List<PersonEntity> personEntities = personService.selectByPositionId(actDefEntity.getDefId());
//            for(PersonEntity personEntity:personEntities){
//                list.add(personEntity.getUserId());
//            }
//        } else if ("role".equals(actDefEntity.getDimension())) {  // 角色
//            List<PersonEntity> personEntities = personService.selectByRoleId(actDefEntity.getDefId());
//            for(PersonEntity personEntity:personEntities){
//                list.add(personEntity.getUserId());
//            }
//        }
//        return list;
//    }
}
