package com.getir.demo.common.response;


/**
 * Author: mcaylak
 * Since : 8.10.2022
 *
 * <p> Customer Monthly Statistics Response </p>
 * |----------------------------------------------------------------|
 * | Month    | TotalOrderCount | TotalPrice | TotalPurchasedAmount |
 * |----------------------------------------------------------------|
 * | String   | Long            | Long       | Long                 |
 * |----------------------------------------------------------------|
 * <p> Return customer monthly statistics! </p>
 */

public interface CustomerMonthlyStatisticsResponse {

    String getMonth();

    Long getTotalOrderCount();

    Long getTotalPrice();

    Long getTotalPurchasedAmount();

}
