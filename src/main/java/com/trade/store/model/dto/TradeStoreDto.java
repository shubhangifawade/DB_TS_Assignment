package com.trade.store.model.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */

@Data
public class TradeStoreDto {
    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private String expired;
}
