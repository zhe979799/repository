package org.zhe.service.s_upload.item.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data  //声明get set方法
@ConfigurationProperties(prefix = "shop.upload")  //配置属性，前缀为shop。upload
public class UploadProperties {
    private String imageUrl;
    private List<String> allowTypes;
}
