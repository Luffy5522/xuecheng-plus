package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Luffy5522
 * @date: 2023/4/15 15:56
 * @description: 修改课程的dto类
 */
@Data
@ToString
public class EditCourseDto extends CourseBaseInfoDto{
    @ApiModelProperty(value = "课程id",required = true)
    private Long courseId;
}
