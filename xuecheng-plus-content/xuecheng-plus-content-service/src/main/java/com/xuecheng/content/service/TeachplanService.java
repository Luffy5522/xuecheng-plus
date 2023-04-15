package com.xuecheng.content.service;

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
}
