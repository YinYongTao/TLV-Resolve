package cn.bsit.annotation;

import java.lang.annotation.*;

/**
 * 作用在模型类字段上的注解
 *
 * @author yt.yin
 * @version 1.0 2017-08-15  18:04:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ModelAnnotation {

    /** 当前属性占据的位置 */
    int placeholder() default 0;

    /** 当前属性要截取的长度，值得一提的是，如果编码类型是 BCD 那么配length是压缩后 BCD 字节数组的长度 */
    int length() default 0;

    /** 编码类型 */
    CodeType CODE_TYPE () default CodeType.ASCII;
}
