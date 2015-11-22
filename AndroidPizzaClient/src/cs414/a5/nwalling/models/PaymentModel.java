/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.enums.PaymentType;
import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static cs414.a5.nwalling.models.UserModel.PROP_AUTHLEVEL;

/**
 *
 * @author Jacob
 */
public class PaymentModel extends AbstractModel implements IPaymentModel {
    public static final transient String PROP_ID = "id";
    public static final transient String PROP_TYPE = "type";
    public static final transient String PROP_APPROVED = "approved";
    public static final transient String PROP_TOTAL = "total";
    public static final transient String PROP_CARDFIRSTNAME = "cardFirstName";
    public static final transient String PROP_CARDLASTNAME = "cardLastName";
    public static final transient String PROP_CARDNUMBER = "cardNumber";
    public static final transient String PROP_EXPRMONTH = "month";
    public static final transient String PROP_EXPRYEAR = "year";
    
    private IOrderModel order;
    
    private int id;
    private PaymentType type;
    private String cardFirstName;
    private String cardLastName;
    private int cardNumber;
    private int expDay;
    private int expMonth;
    private int expYear;
    
    private double total;
    private int orderType;
    
    public PaymentModel()
    {
        //Do not use this is a hack
    }
    public PaymentModel(IModelFactory f,IDataSource source) 
    {
        super();
        this.source = source;
        order = f.getEmptyIOrderModel();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPaymentType() {
        return type.toString();
    }

    @Override
    public void setPaymentType(int type) {
        this.type = PaymentType.getPaymentType(type);
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public String getCardFirstName() {
        return cardFirstName;
    }

    @Override
    public void setCardFirstName(String value) {
        this.cardFirstName = value;
    }
    
    @Override
    public String getCardLastName() {
        return cardLastName;
    }

    @Override
    public void setCardLastName(String value) {
        this.cardLastName = value;
    }
    
    @Override
    public int getExpDay() {
        return expDay;
    }

    @Override
    public void setExpDay(int value) {
        this.expDay = value;
    }
    
    @Override
    public int getExpMonth() {
        return expMonth;
    }

    @Override
    public void setExpMonth(int value) {
        this.expMonth = value;
    }
    
    @Override
    public int getExpYear() {
        return expYear;
    }

    @Override
    public void setExpYear(int value) {
        this.expYear = value;
    }
    @Override
    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public void setCardNumber(int value) {
        this.cardNumber = value;
    }

    @Override
    public int getOrderType() {
        return this.orderType;
    }

    @Override
    public void setOrderType(int value) {
        this.orderType = value;
    }

    @Override
    public String getCustomerName() {
        return order.getCustomerName();
    }

    @Override
    public void setCustomerName(String value) {
        order.setCustomerName(value);
    }

    @Override
    public String getCustomerAddress1() {
        return order.getCustomerAddress1();
    }

    @Override
    public void setCustomerAddress1(String value) {
        order.setCustomerAddress1(value);
    }

    @Override
    public String getCustomerAddress2() {
        return order.getCustomerAddress2();
    }

    @Override
    public void setCustomerAddress2(String value) {
        order.setCustomerAddress2(value);
    }

    @Override
    public String getCustomerCity() {
        return order.getCustomerCity();
    }

    @Override
    public void setCustomerCity(String value) {
        order.setCustomerCity(value);
    }

    @Override
    public int getCustomerZip() {
        return order.getCustomerZip();
    }

    @Override
    public void setCustomerZip(int value) {
        order.setCustomerZip(value);
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean save() {
        try {
            this.setTotal(order.getTotal());
            source.saveOrder(order);
            source.savePayment(this);
        } catch (StorageException ex) {
            return false;
        }
        return true;
    }

    @Override
    public void load(HashMap<String, Object> fields) throws LoadException {
        if(fields == null || fields.keySet().size() < 1)
        {
            throw new LoadException("PaymentModel: No fields passed in!");
        }
        if(fields.containsKey(PROP_ID.toLowerCase()))
            setId(Integer.parseInt(fields.get(PROP_ID.toLowerCase()).toString()));

        if(fields.containsKey(PROP_TOTAL.toLowerCase()+"id"))
            setTotal(Double.parseDouble(fields.get(PROP_AUTHLEVEL.toLowerCase()+"id").toString()));
    }

    
}
