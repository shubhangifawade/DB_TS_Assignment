package com.trade.store.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.Serializable;
import java.util.UUID;

public class IdentifierGenerator implements org.hibernate.id.IdentifierGenerator {
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}
