package com.trade.store.model.dto;

import com.trade.store.model.entity.TradeStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseDto {
    private String testCaseMessage;
    private TradeStore tradeStore;
}
