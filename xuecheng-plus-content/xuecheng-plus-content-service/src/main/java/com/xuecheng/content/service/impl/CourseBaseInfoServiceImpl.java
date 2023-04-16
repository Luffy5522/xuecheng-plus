package com.xuecheng.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.constant.CourseBaseEnum;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description 课程基本信息查询
 */
@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Resource
    CourseBaseMapper courseBaseMapper;

    @Resource
    CourseMarketMapper courseMarketMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto) {

        //拼装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //根据名称模糊查询,在sql中拼接 course_base.name like '%值%'
        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()), CourseBase::getName, courseParamsDto.getCourseName());
        //根据课程审核状态查询 course_base.audit_status = ?
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, courseParamsDto.getAuditStatus());
        //按课程发布状态查询
        //queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getPublishStatus()), CourseBase::getStatus, courseParamsDto.getPublishStatus());

        //创建page分页参数对象，参数：当前页码，每页记录数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        //开始进行分页查询
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        //数据列表
        List<CourseBase> items = pageResult.getRecords();
        //总记录数
        long total = pageResult.getTotal();

        //List<T> items, long counts, long page, long pageSize
        return new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());


    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {

        // 1.对AddCourse进行判断是否合规
        if (StrUtil.isBlank(addCourseDto.getName())) {
            log.info("课程名字不能为空");
            throw new XueChengPlusException("课程名字不能为空");
        }
        if (StrUtil.isBlank(addCourseDto.getUsers())) {
            log.info("使用人群不能为空");
            throw new RuntimeException("使用人群不能为空");
        }
        if (StrUtil.isBlank(addCourseDto.getMt())) {
            log.info("课程分类不能为空");
            throw new RuntimeException("课程分类不能为空");
        }

        if (StrUtil.isBlank(addCourseDto.getSt())) {
            log.info("课程分类不能为空");
            throw new RuntimeException("课程分类不能为空");
        }
        if (StrUtil.isBlank(addCourseDto.getGrade())) {
            log.info("课程等级不能为空");
            throw new RuntimeException("课程等级不能为空");
        }

        if (StrUtil.isBlank(addCourseDto.getCharge())) {
            log.info("收费情况不能为空");
            throw new RuntimeException("收费情况不能为空");
        }
        // 1.1 判断收费情况
        if (addCourseDto.getCharge().equals(CourseBaseEnum.CHARGE_YES.getCode())) {
            // 若收费,则价格不能为空或0
            Float price = addCourseDto.getPrice();
            if (price <= 0) {
                log.info("价格不能小于0");
                throw new RuntimeException("价格不能小于0");
            }
        }

        // 2.进行添加
        // 分为课程基本信息表和课程营销表
        CourseBase courseBase = new CourseBase();
        BeanUtil.copyProperties(addCourseDto, courseBase);
        createCourseMarket(addCourseDto);

        // 3.填充信息
        courseBase.setAuditStatus(CourseBaseEnum.AUDIT_NOT_PASS.getCode());
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setChangeDate(LocalDateTime.now());
        courseBase.setCompanyId(companyId);
        courseBase.setStatus(CourseBaseEnum.PUBLISH_NOT.getCode());

        // 4.利用courseBaseMapper进行添加
        courseBaseMapper.insert(courseBase);

        // 5. 创造CourseBaseInfoDto进行返回
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtil.copyProperties(courseBase, courseBaseInfoDto);
        return courseBaseInfoDto;
    }

    @Transactional
    @Override
    public CourseBaseInfoDto UpdateCourseBase(EditCourseDto editCourseDto) {
        // 进行修改
        // 注意进行修改的信息要符合规范
        CourseBase courseBase = new CourseBase();
        CourseMarket courseMarket = new CourseMarket();

        // 进行属性赋值
        BeanUtil.copyProperties(editCourseDto, courseBase);
        BeanUtil.copyProperties(editCourseDto, courseMarket);

        // 对数据库进行修改
        courseBaseMapper.updateById(courseBase);
        courseMarketMapper.updateById(courseMarket);

        // 返回修改后的数据
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtil.copyProperties(editCourseDto, courseBaseInfoDto);

        return courseBaseInfoDto;

    }

    @Override
    public CourseBaseInfoDto getCourseBase(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtil.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtil.copyProperties(courseMarket, courseBaseInfoDto);
        return courseBaseInfoDto;
    }

    @Transactional
    @Override
    public void deleteCourseBase(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            throw new XueChengPlusException("课程id不存在");
        }
        if (courseBase.getAuditStatus().equals(CourseBaseEnum.AUDIT_COMMIT.getCode())) {
            throw new XueChengPlusException("正在审核中,不可删除");
        }

        courseBaseMapper.deleteById(courseId);
        courseMarketMapper.deleteById(courseId);

    }

    private void createCourseMarket(AddCourseDto addCourseDto) {
        CourseMarket courseMarket = new CourseMarket();
        BeanUtil.copyProperties(addCourseDto, courseMarket);
        courseMarketMapper.insert(courseMarket);
    }


}
