package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @Author Luffy5522
 * @date: 2023/4/15 16:38
 * @descPiption: 课程基本信息管理业务接口
 */
public interface TeachplanService {

    /**
     * @param courseId :课程id
     * @return List<TeachPlanDto>
     * @author Luffy5522
     * @description 查询课程计划树型结构
     * @date 2023/4/15 16:38
     */
    List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * @param teachplan: 课程计划
     * @return void
     * @author Luffy5522
     * @description 创建或修改课程计划
     * @date 2023/4/15 17:29
     */
    void saveTeachplan(SaveTeachplanDto teachplan);

    /**
     * @param id:  课程id
     * @return void
     * @author Luffy5522
     * @description 根据id删除课程计划
     * @date 2023/4/15 20:42
     */
    void deleteTeachplan(Long id);

}
