package com.xuecheng.base.constant;

/**
 * @Author Luffy5522
 * @date: 2023/4/14 9:58
 * @description:
 */
public enum CourseBaseEnum {

    // 课程审核状态
    AUDIT_NOT_PASS("202001", "审核未通过"),
    AUDIT_NOT_COMMIT("202002", "课程未提交"),
    AUDIT_COMMIT("202003", "课程已提交"),
    AUDIT_PASS("202004", "审核通过"),

    // 课程发布状态
    PUBLISH_NOT("203001", "课程未发布"),
    PUBLISH_PASS("203002", "课程已发布"),
    PUBLISH_OUT_OF("203003", "课程已下线"),

    // 课程收费情况
    CHARGE_NO("201000", "免费"),
    CHARGE_YES("201001", "收费");

    private final String code;
    private final String describe;

    CourseBaseEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
