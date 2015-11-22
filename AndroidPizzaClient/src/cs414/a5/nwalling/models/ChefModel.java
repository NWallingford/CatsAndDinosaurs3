/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.exceptions.LoadException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jacob
 */
public class ChefModel extends AbstractModel implements IChefModel {

    ArrayList<IOrderModel> orders = new ArrayList();
    public ChefModel()
    {
        //Do not use this is a hack
    }
    public ChefModel(IDataSource source)
    {
        this.source = source;
    }
    @Override
    public boolean save() {
        boolean savedGood = true;
        savedGood = orders.stream().map((order) -> order.save()).reduce(savedGood, (accumulator, _item) -> accumulator & _item);
        return savedGood;
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(HashMap<String, Object> fields) throws LoadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getOrders() {
        //Not completed orders are marked as 1
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getCompletedOrders() {
        //Completed Orders are marked as 3 and up you should really only worry about 3 and 1 though. The others were wishfull thinking.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setOrders(ArrayList<IOrderModel> value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void markDone(int orderIdex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
