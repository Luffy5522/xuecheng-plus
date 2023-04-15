package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程信息管理接口
 * @date 2023/2/12 10:14
 */
public interface CourseBaseInfoService {

    /**
     * 课程分页查询
     *
     * @param pageParams      分页查询参数
     * @param courseParamsDto 查询条件
     * @return 查询结果
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto);

    /**
     * 新增课程
     *
     * @param companyId    机构id
     * @param addCourseDto 课程信息
     * @return 课程详细信息
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);


    /**
     * @param editCourseDto: 前端传进来的dto
     * @return CourseBaseInfoDto
     * @author Luffy5522
     * @description 根据id修改课程
     * @date 2023/4/15 15:59
     */
    CourseBaseInfoDto UpdateCourseBase(EditCourseDto editCourseDto);

    /**
     * @param courseId: 课程id
     * @return CourseBaseInfoDto
     * @author Luffy5522
     * @description 根据课程id查询课程信息
     * @date 2023/4/15 16:00
     */
    CourseBaseInfoDto getCourseBase(Long courseId);


}
