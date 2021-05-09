package com.trade.store.common;

import java.time.format.DateTimeFormatter;

/**
 * @Create: 09-May-21
 * @Author Shubhangi
 */
public class CommonConstants {
    public static final String ADD_TSTORE_POST_URL = "/api/addTradeStore";

    public static final String EXCEPTION = "Lower version is being received by store";
    public static final String CASE1 = "1. Version is same so it will override the existing record.";
    public static final String CASE2 = "2. Store should not allow the trade which has less maturity date then today date.";
    public static final String CASE3 = "3. Store should automatically update expire flag if in a store the trade crosses the maturity date.";
    public static final String NEW_STORE = "New store added";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
