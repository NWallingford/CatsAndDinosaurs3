/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.enums.PaymentType;
import cs414.a5.nwalling.models.IModel;
import cs414.a5.nwalling.models.IOrderModel;
import cs414.a5.nwalling.models.IPaymentModel;
import cs414.a5.nwalling.models.PaymentModel;

/**
 *
 * @author Jacob
 */
public class PaymentController extends AbstractController implements IPaymentController {

    private IPaymentModel model;
    private IModelFactory modelFactory;
    
    public PaymentController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.modelFactory = modelFactory;
        model = modelFactory.getEmptyIPaymentModel();
    }
    
    @Override
    public boolean submit() {
        return model.save();
    }
    
    //You should do validation in this method. If it's good set it in the model
    //Otherwise return false so you can display an error
    @Override
    public boolean setCardFirstName(String value) {
        model.setCardFirstName(value);
        return true;
    }

    @Override
    public boolean setCardLastName(String value) {
        model.setCardLastName(value);
        return true;
    }

    @Override
    public boolean setCardNumber(int value) {
        model.setCardNumber(value);
        return true;
    }

    @Override
    public boolean setExpDay(int value) {
        model.setExpDay(value);
        return true;
    }

    @Override
    public boolean setExpMonth(int value) {
        model.setExpMonth(value);
        return true;
    }

    @Override
    public boolean setExpYear(int value) {
        model.setExpYear(value);
        return true;
    }

    @Override
    public boolean setOrderType(int value) {
        model.setOrderType(value);
        return true;
    }

    @Override
    public boolean setCustomerName(String value) {
        model.setCustomerName(value);
        return true;
    }

    @Override
    public boolean setCustomerAddress1(String value) {
        model.setCustomerAddress1(value);
        return true;
    }

    @Override
    public boolean setCustomerAddress2(String value) {
        model.setCustomerAddress2(value);
        return true;
    }

    @Override
    public boolean setCustomerCity(String value) {
        model.setCustomerCity(value);
        return true;
    }

    @Override
    public boolean setCustomerZip(int value) {
        model.setCustomerZip(value);
        return true;
    }

    @Override
    public boolean setTotal(double value) {
        model.setTotal(value);
        return true;
    }
    
}
