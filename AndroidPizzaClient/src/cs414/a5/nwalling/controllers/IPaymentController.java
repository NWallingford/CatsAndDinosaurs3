/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

/**
 *
 * @author Jacob
 */
public interface IPaymentController {
    boolean setCardFirstName(String value);
    boolean setCardLastName(String value);
    boolean setCardNumber(int value);
    boolean setExpDay(int value);
    boolean setExpMonth(int value);
    boolean setExpYear(int value);
    boolean setOrderType(int value);
    boolean setCustomerName(String value);
    boolean setCustomerAddress1(String value);
    boolean setCustomerAddress2(String value);
    boolean setCustomerCity(String value);
    boolean setCustomerZip(int value);
    boolean setTotal(double value);
    
    boolean submit();
}
