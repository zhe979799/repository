package org.zhe.common.s_common.item.vo;

import lombok.Data;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;

@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long tiomstamp;

    public ExceptionResult(ExceptionEnum e) {
        this.status = e.getCode();
        this.message = e.getMsg();
        this.tiomstamp = System.currentTimeMillis();
    }
}
