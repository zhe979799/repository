package org.zhe.common.s_common.item.enums;


public enum ExceptionEnum {                                 //异常失败枚举
    CATEGORY_NOT_FOND(400,"商品分类查询失败！"),
    DONT_MODIFY_DATA(403,"请不要修改数据! "),
    BRAND_NOT_FOND(400,"品牌数据查询失败! "),
    BRAND_NOT_CODE(400,"品牌数据写入失败! "),
    IMAGE_UPLOAD_FAILED(400,"图片上传失败! "),
    IMAGE_TYPE_FAILED(400,"图片类型不匹配! "),
    IMAGE_DATA_FAILED(400,"图片不能为空! "),
    IMAGE_CHECK_FAILED(400,"图片检验失败! "),
    BRAND_DELETE_FAILED(404,"品牌删除失败! "),
    SPEC_GROUP_NOT_FOND(404,"品类组信息查询失败! "),
    SPEC_PARAM_NOT_FOND(404,"品类参数信息查询失败! "),
    BRAND_EDIT_FAILED(400,"品牌数据修改失败! "),
    SPEC_PARAM_DELETE_FAILED(400,"规格参数删除失败! "),
    SPEC_GROUP_CODE_FAILED(400,"分组信息写入失败! "),
    SPEC_PARAM_CODE_FAILED(400,"详细信息写入失败! "),
    GOODS_NOT_FOND(400,"商品数据查询失败! "),
    GOODS_LOAD_NAME_FAILED(400,"商品数据转换失败! "),
    GOODS_SPU_CODE_FAILED(400,"SPU数据写入失败! "),
    GOODS_SKU_DATE_FAILED(400,"SKU数据为空! "),
    GOODS_SPUDETAIL_DATE_FAILED(400,"SPUDETAIL数据为空! "),
    GOODS_SPUDETAIL_CODE_FAILED(400,"SPUDETAIL数据写入失败! "),
    GOODS_SPUDETAIL_NOT_FOND(400,"SPUDETAIL数据查询失败! "),
    GOODS_SKU_CODE_FAILED(400,"SKU数据写入失败! "),
    GOODS_SKU_NOT_FOND(400,"SKU数据查询失败! "),
    GOODS_STOCK_DATA_FAILED(400,"STOCK数据写入失败! "),
    ;

    private int code; //状态码
    private String msg; //错误信息

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
