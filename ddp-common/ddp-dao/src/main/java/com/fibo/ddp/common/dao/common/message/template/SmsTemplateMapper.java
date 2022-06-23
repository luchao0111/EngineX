package com.fibo.ddp.common.dao.common.message.template;

import com.fibo.ddp.common.model.common.message.template.entity.SmsTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信模板表(SmsTemplate)表数据库访问层
 *
 * @author andy.wang
 * @since 2022-01-07 18:11:16
 */
@Mapper
public interface SmsTemplateMapper extends BaseMapper<SmsTemplate>{

}
