package com.trade.store.repository;

import com.trade.store.model.entity.TradeStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore, String> {
}
