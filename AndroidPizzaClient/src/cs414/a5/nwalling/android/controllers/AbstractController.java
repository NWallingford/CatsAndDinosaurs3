/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Observable;

import cs414.a5.nwalling.android.data.IDataSource;
/**
 *
 * @author Jacob
 */
public abstract class AbstractController extends Observable implements IController, Serializable
{
    protected PropertyChangeSupport propertySupport;

    protected IDataSource source;
    
    public AbstractController(IDataSource source)
    {
        this.source = source;
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
