package com.trade.store.service.impl;

import com.trade.store.common.CommonConstants;
import com.trade.store.model.dto.ResponseDto;
import com.trade.store.model.entity.TradeStore;
import com.trade.store.repository.TradeStoreRepository;
import com.trade.store.service.ITradeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.trade.store.common.CommonConstants.*;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@Service
@RequiredArgsConstructor
public class TradeStoreService implements ITradeStoreService {

    private final TradeStoreRepository tradeStoreRepository;
//    private final TradeStoreMapper tradeStoreMapper;

    @Override
    public ResponseDto addTradeStore(TradeStore tradeStore) {


        LocalDate maturityDate = tradeStore.getMaturityDate();
        String todaysDateStr = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate todaysDate = LocalDate.parse(todaysDateStr, DATE_TIME_FORMATTER);
        ResponseDto responseDto = null;
        if (tradeStoreRepository.count() == 0) {
            tradeStore = tradeStoreRepository.save(tradeStore);
            responseDto = ResponseDto.builder().
                    testCaseMessage(NEW_STORE).
                    tradeStore(tradeStore).build();
        } else {
            TradeStore dbTradeStore = tradeStoreRepository.findById(tradeStore.getTradeId()).get();

            if (dbTradeStore != null) {
                if (tradeStore.getVersion() < dbTradeStore.getVersion()) {
                    responseDto = ResponseDto.builder().
                            testCaseMessage(EXCEPTION).build();
                    throw new RuntimeException(EXCEPTION);
                }
                if (tradeStore.getVersion() == dbTradeStore.getVersion()) {
                    tradeStore.setTradeId(dbTradeStore.getTradeId());
                    tradeStore = tradeStoreRepository.save(tradeStore);
                    responseDto = ResponseDto.builder().
                            testCaseMessage(CASE1).
                            tradeStore(tradeStore).build();
                }

                if (todaysDate.isAfter(maturityDate)) {
                    tradeStore.setTradeId(dbTradeStore.getTradeId());
                    tradeStore.setExpired("Y");
                    tradeStore = tradeStoreRepository.save(tradeStore);
                    responseDto = ResponseDto.builder().
                            testCaseMessage(CASE3).
                            tradeStore(tradeStore).build();
                }
            } else {
                if (maturityDate.isBefore(todaysDate)) {
                    responseDto = ResponseDto.builder().
                            testCaseMessage(CASE2).build();
                    return responseDto;
                } else {
                    tradeStore = tradeStoreRepository.save(tradeStore);
                    responseDto = ResponseDto.builder().
                            testCaseMessage(NEW_STORE).
                            tradeStore(tradeStore).build();
                }
            }
        }
        return responseDto;
    }
}
