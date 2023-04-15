package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @Author Luffy5522
 * @date: 2023/4/15 16:30
 * @description:
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class TeachplanDto extends Teachplan {

    // 课程计划关联的媒资信息
    TeachplanMedia teachplanMedia;

    // 子结点
    List<TeachplanDto> teachPlanTreeNodes;
}
