package org.hswebframework.web.service.form.simple.dict;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hswebframework.ezorm.core.OptionConverter;
import org.hswebframework.ezorm.core.ValueConverter;
import org.hswebframework.web.dict.DictDefineRepository;
import org.hswebframework.web.dict.EnumDict;
import org.hswebframework.web.entity.form.DictConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;


/**
 * @author zhouhao
 * @since 3.0
 */
@Component
@SuppressWarnings("all")
public class DictionaryOptionalConvertBuilderStrategy implements OptionalConvertBuilderStrategy {
    @Autowired(required = false)
    private DictDefineRepository dictDefineRepository;

    @Override
    public boolean support(String type) {
        return "dict".equals(type) && dictDefineRepository != null;
    }


    @Override
    public OptionConverter build(DictConfig dictConfig) {
        JSONObject conf = JSON.parseObject(dictConfig.getConfig());
        String dictId = conf.getString("dictId");
        String fieldName = conf.getString("fieldName");
        String sppliter = conf.getString("sppliter");
        String writeObject = conf.getString("writeObject");
        EnumDictOptionConverter<EnumDict<Object>> converter = new EnumDictOptionConverter<>(() -> dictDefineRepository.getDefine(dictId).getItems(), fieldName);

        converter.setWriteObject(!"false".equalsIgnoreCase(writeObject));

        if (!StringUtils.isEmpty(sppliter)) {
            converter.setSplitter(str -> Arrays.asList(str.split(sppliter)));
        }

        return converter;
    }

    @Override
    public ValueConverter buildValueConverter(DictConfig dictConfig) {
        JSONObject conf = JSON.parseObject(dictConfig.getConfig());
        String dictId = conf.getString("dictId");

        EnumDictValueConverter<EnumDict<Object>> converter =
                new EnumDictValueConverter<>(() -> dictDefineRepository.getDefine(dictId).getItems());

        return converter;
    }
}
