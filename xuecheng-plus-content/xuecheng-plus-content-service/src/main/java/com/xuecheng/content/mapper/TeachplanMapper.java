package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * @param courseId:
     * @return List<TeachPlanDto>
     * @author Luffy5522
     * @description 查询课程计划, 组成树形结构
     * @date 2023/4/15 16:36
     */
    List<TeachplanDto> selectTreeNodes(long courseId);
}
