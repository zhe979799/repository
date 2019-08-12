package org.zhe.service.s_upload.item.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.service.s_upload.item.config.UploadProperties;
import org.zhe.service.s_upload.item.service.UploadService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("uploadService")
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadProperties uploadProperties;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if(!uploadProperties.getAllowTypes().contains(contentType)){
                throw new ShopException(ExceptionEnum.IMAGE_TYPE_FAILED);
            }
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null){
                throw new ShopException(ExceptionEnum.IMAGE_DATA_FAILED);
            }
            String type = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), type, null);
            return uploadProperties.getImageUrl()+storePath.getFullPath();
        } catch (IOException e) {
            log.error("[文件上传] 图片上传失败!",e);
            throw  new ShopException(ExceptionEnum.IMAGE_UPLOAD_FAILED);
        }
    }
}
