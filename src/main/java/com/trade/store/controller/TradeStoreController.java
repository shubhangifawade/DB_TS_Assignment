package com.trade.store.controller;

import com.trade.store.model.dto.ResponseDto;
import com.trade.store.model.entity.TradeStore;
import com.trade.store.service.ITradeStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
@RestController
@Slf4j
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class TradeStoreController {

    private final ITradeStoreService iTradeStoreService;

    @PostMapping(value = "/addTradeStore")
    public ResponseEntity<ResponseDto> addTradeStore(@RequestBody TradeStore tradeStore) {

        log.debug("Executing request for addTradeStore {}", tradeStore);
        ResponseDto tradeStoreResponse = iTradeStoreService.addTradeStore(tradeStore);
        return new ResponseEntity<>(tradeStoreResponse, HttpStatus.OK);
    }

}
