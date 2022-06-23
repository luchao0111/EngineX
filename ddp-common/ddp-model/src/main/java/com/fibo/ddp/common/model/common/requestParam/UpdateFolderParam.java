package com.fibo.ddp.common.model.common.requestParam;

import com.fibo.ddp.common.model.common.enums.ErrorCodeEnum;
import com.fibo.ddp.common.utils.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class UpdateFolderParam {
    private List<Long> ids;//规则id
    private Long folderId;//文件夹id

    public static boolean checkNotNull(UpdateFolderParam param){
        if (param==null||param.ids==null||param.ids.isEmpty()||param.folderId==null){
            throw new ApiException(ErrorCodeEnum.PARAMS_EXCEPTION.getCode(),"id或者文件夹id为空");
        }
        return true;
    }
}
