/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.exceptions.LoadException;
import java.util.HashMap;

/**
 *
 * @author Jacob
 */
public interface IModel {
    public boolean save();
    public void clear();
    public void init(IDataSource source);
    /*
    The load method takes a hashmap of string object and 
    uses it to place the provided values in the model.
    
    It expects the key names to be all lowercase and identical
    to the property name.
    */
    public void load(HashMap<String,Object> fields) throws LoadException;
}
