package com.trade.store.common;

import com.trade.store.model.entity.TradeStore;
import com.trade.store.repository.TradeStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.trade.store.common.CommonConstants.DATE_TIME_FORMATTER;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final TradeStoreRepository tradeStoreRepository;

    @Scheduled(cron = "0 * 9 * * ?")
    public void cronJobSch() {
        List<TradeStore> tradeStores = tradeStoreRepository.findAll();
        tradeStores.forEach(tradeStore -> {
            LocalDate maturityDate = LocalDate.parse(tradeStore.getMaturityDate().format(DATE_TIME_FORMATTER));
            LocalDate todaysDate = LocalDate.parse(LocalDate.now().format(DATE_TIME_FORMATTER));
            if (maturityDate.isAfter(todaysDate)) {
                tradeStore.setExpired("Y");
                tradeStoreRepository.save(tradeStore);
            }
        });
    }
}