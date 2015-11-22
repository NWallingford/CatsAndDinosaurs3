/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

/**
 *
 * @author Jacob
 */
public interface IPaymentModel extends IModel {
    String getPaymentType();
    void setPaymentType(int value);
    String getCardFirstName();
    void setCardFirstName(String value);
    String getCardLastName();
    void setCardLastName(String value);
    int getCardNumber();
    void setCardNumber(int value);
    int getExpDay();
    void setExpDay(int value);
    int getExpMonth();
    void setExpMonth(int value);
    int getExpYear();
    void setExpYear(int value);
    int getOrderType();
    void setOrderType(int value);
    String getCustomerName();
    void setCustomerName(String value);
    String getCustomerAddress1();
    void setCustomerAddress1(String value);
    String getCustomerAddress2();
    void setCustomerAddress2(String value);
    String getCustomerCity();
    void setCustomerCity(String value);
    int getCustomerZip();
    void setCustomerZip(int value);
    double getTotal();
    void setTotal(double value);
}
