package com.qsmaxmin.qsbase.common.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/6/29 15:12
 * @Description 表单参数注解，POST等请求时被注解的参数将被放入表单中
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface FormParam {
    String value();
}