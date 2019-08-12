package org.zhe.service.s_upload.item.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {


    String uploadImage(MultipartFile file);

}
