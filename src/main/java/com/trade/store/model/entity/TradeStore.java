package com.trade.store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */

@Data
@Entity
@Table(name = "trade_store")
@NoArgsConstructor
@AllArgsConstructor
public class TradeStore implements Serializable {
    @Id
//    @GeneratedValue(generator = "generator")
//    @GenericGenerator(name = "generator", strategy = "com.trade.store.utils.IdentifierGenerator")
//    @Basic(optional = false)
    @Column(name = "trade_id")
    @Access(AccessType.PROPERTY)
    private String tradeId;

    @Column(name = "version")
    private int version;

    @Column(name = "counter_party_id")
    private String counterPartyId;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "expired")
    private String expired;
}
