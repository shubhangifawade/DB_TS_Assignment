package com.trade.store.service;

import com.trade.store.model.dto.ResponseDto;
import com.trade.store.model.entity.TradeStore;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
public interface ITradeStoreService {

    ResponseDto addTradeStore(TradeStore tradeStore);
}
