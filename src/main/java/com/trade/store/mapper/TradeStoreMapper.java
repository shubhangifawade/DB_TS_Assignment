package com.trade.store.mapper;

import com.trade.store.model.dto.TradeStoreDto;
import com.trade.store.model.entity.TradeStore;
import org.mapstruct.Mapper;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@Mapper(componentModel = "spring")
public interface TradeStoreMapper {
    TradeStore toEntity(TradeStoreDto tradeStoreDto);
}
