package com.fibo.ddp.common.model.strategyx.scorecard.vo;

import com.fibo.ddp.common.model.strategyx.scorecard.ScorecardDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScorecardDetailVo extends ScorecardDetail implements Serializable {
    private static final long serialVersionUID = 4920595561931964004L;

    private String fieldName;
    private String condition;  // condition表
    private List<ScorecardDetailVo> children;  // 树
}
