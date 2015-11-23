/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;

import com.google.gson.TypeAdapter;

import cs414.a5.nwalling.android.data.IDataSource;

/**
 *
 * @author Jacob
 */
public abstract class AbstractModel implements IModel, Serializable{
//    protected transient IDataSource source;
    
    protected transient PropertyChangeSupport propertySupport;
    
    public AbstractModel()
    {
        propertySupport = new PropertyChangeSupport(this);
    }
    public AbstractModel(IDataSource source)
    {
        this();
//        this.source = source;
    }
    
    public void init(IDataSource source)
    {
//        this.source = source;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
