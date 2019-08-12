package org.zhe.common.s_common.item.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zhe.common.s_common.item.enums.ExceptionEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShopException extends RuntimeException{

    private ExceptionEnum exceptionEnum;


}
