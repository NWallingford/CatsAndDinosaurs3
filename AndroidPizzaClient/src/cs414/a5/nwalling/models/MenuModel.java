/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jacob
 */
public class MenuModel extends AbstractModel implements IMenuModel {
    public static final String PROP_ITEMS = "items";
    private ArrayList<IItemModel> items;
    private IModelFactory modelFactory;
    public MenuModel()
    {
        //Do not use this is a hack
    }
    public MenuModel(IModelFactory modelFactory, IDataSource source) {
        super();
        items = new ArrayList();
        this.source = source;
    }
    
    public void newItem(String name, double price)
    {
        IItemModel tmp = modelFactory.getEmptyIItemModel();
        tmp.setName(name);
        tmp.setPrice(price);
        items.add(tmp);
    }
   
    public void removeItem(ItemModel item)
    {
        items.remove(item);
    }
    
    public ArrayList<IItemModel> getItems()
    {
        return items;
    }
    
    public void newSpecialPrice(int index, double price)
    {
    }
    
    public void revokeSpecial(int[] indecies)
    {
    }
    
    @Override
    public boolean save() 
    {
        boolean savedGood = true;
        for(IItemModel item : items)
        {
            savedGood &= item.save();
        }
        
        return savedGood;
    }

    @Override
    public void load(HashMap<String, Object> fields) {
        
    }

    @Override
    public void clear() {
    
    }

    @Override
    public void setItems(ArrayList<IItemModel> value) {
        this.items = value;
    }
}
