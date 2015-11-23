/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.models;

import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.exceptions.StorageException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jacob
 */
public class OrderModel extends AbstractModel implements IOrderModel{
    public static final transient String PROP_ID = "id";
    public static final transient String PROP_USER = "user";
    public static final transient String PROP_STATUS = "status";
    public static final transient String PROP_ITEMS = "items";
    public static final transient String PROP_ITEMIDS = "itemIds";
    public static final transient String PROP_ITEMPRICES = "itemPrices";
    public static final transient String PROP_TOTAL = "total";
    public static final transient String PROP_PAYMENTID = "paymentID";
    public static final transient String PROP_ORDERITEMIDS = "orderItemIds";
    public static final transient String PROP_CUSTOMERNAME = "customerName";
    public static final transient String PROP_CUSTOMERADDRESS1 = "customerAddress1";
    public static final transient String PROP_CUSTOMERADDRESS2 = "customerAddress2";
    public static final transient String PROP_CUSTOMERCITY = "customerCity";
    public static final transient String PROP_CUSTOMERZIP = "customerZip";
    
    private int id;
    private int user;
    private int status;
    private int paymentID; //int stores the associated payment ID

    private String customerName;
    private String customerAddress1;
    private String customerAddress2;
    private String customerCity;
    private int customerZip;
    
    private ArrayList<IItemModel> items;
    

    public OrderModel() {
        super();
//        this.source = source;
        items = new ArrayList();
    }
    
    public int getPaymentID() 
    {
        return paymentID;
    }
    public void setPaymentID(int paymentID) 
    {
        id = paymentID;
    }
    //<editor-fold desc="Id">
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    //</editor-fold>
    //<editor-fold desc="User">
    public int getUser()
    {
        if(user < 1)
            return 2;
        return user;
    }
    public void setUser(int value)
    {
        user = value;
    }
    //</editor-fold>
    //<editor-fold desc="Status">
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int value)
    {
        status = value;
    }
    //</editor-fold>
    //<editor-fold desc="Items">
    public ArrayList<IItemModel> getItems()
    {
        return items;
    }
    public void setItems (ArrayList<IItemModel> value)
    {
        items = value;
    }
    //</editor-fold>
    
    //<editor-fold desc="Total">
    private double total;
    @Override
    public double getTotal()
    {
        double total = 0;
        for(IItemModel item : items)
        {
            total += item.getPrice();
        }
        return total;
    }
    
    @Override
    public void setTotal (double value)
    {
        total = value;
    }
    
    private double calculateTotal()
    {
        double t = 0;
        for(IItemModel item : items)
        {
            t += item.getDisplayPrice();
        }
        return t;
    }
    //</editor-fold>
    
//    @Override
//    public boolean save() 
//    {
//        
//        try {
//            source.saveOrder(this);
//        } catch (StorageException ex) {
//            return false;
//        }
//        return true;
//    }


    @Override
    public void load(HashMap<String, Object> fields) throws LoadException {
        if(fields == null || fields.keySet().size() < 1)
        {
            throw new LoadException("ItemModel: No fields passed in!");
        }
        
        if(fields.containsKey(PROP_ID))
            setId(Integer.parseInt(fields.get(PROP_ID).toString()));
        if(fields.containsKey(PROP_USER+"id"))
            setUser(Integer.parseInt(fields.get(PROP_USER+"id").toString()));
        if(fields.containsKey(PROP_STATUS+"id"))
            setStatus(Integer.parseInt(fields.get(PROP_STATUS+"id").toString()));
        if(fields.containsKey(PROP_PAYMENTID+"id"))
            setStatus(Integer.parseInt(fields.get(PROP_PAYMENTID+"id").toString()));
        if(fields.containsKey(PROP_CUSTOMERNAME.toLowerCase()))
            setCustomerName(fields.get(PROP_CUSTOMERNAME.toLowerCase()).toString());
        if(fields.containsKey(PROP_CUSTOMERADDRESS1.toLowerCase()))
            setCustomerAddress1(fields.get(PROP_CUSTOMERADDRESS1.toLowerCase()).toString());
        if(fields.containsKey(PROP_CUSTOMERADDRESS2.toLowerCase()))
            setCustomerAddress2(fields.get(PROP_CUSTOMERADDRESS2.toLowerCase()).toString());
        if(fields.containsKey(PROP_CUSTOMERCITY.toLowerCase()))
            setCustomerCity(fields.get(PROP_CUSTOMERCITY.toLowerCase()).toString());
        if(fields.containsKey(PROP_CUSTOMERNAME.toLowerCase()))
            setCustomerZip(Integer.parseInt(fields.get(PROP_CUSTOMERZIP.toLowerCase()).toString()));
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItem(ItemModel item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItem(ItemModel item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void setCustomerName(String value) {
        customerName = value;
    }

    @Override
    public String getCustomerAddress1() {
        return customerAddress1;
    }

    @Override
    public void setCustomerAddress1(String value) {
        customerAddress1 = value;
    }

    @Override
    public String getCustomerAddress2() {
        return customerAddress2;
    }

    @Override
    public void setCustomerAddress2(String value) {
        customerAddress2 = value;
    }

    @Override
    public String getCustomerCity() {
        return customerCity;
    }

    @Override
    public void setCustomerCity(String value) {
        customerCity = value;
    }

    @Override
    public int getCustomerZip() {
        return customerZip ;
    }

    @Override
    public void setCustomerZip(int value) {
        customerZip = value;
    }

    
}
