/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.models.IMenuModel;

/**
 *
 * @author Jacob
 */
public class ChangeMenuController extends AbstractController implements IChangeMenuController{

    private IMenuModel model;
    private IModelFactory modelFactory;
    public ChangeMenuController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.model = modelFactory.getEmptyIMenuModel();
    }

    @Override
    public void removeItems(int[] indices)
    {
    }
    
    @Override
    public void newItem(String name, double price)
    {
    }
    
    @Override
    public void revokeSpecial(int[] indecies)
    {
    }
    
    @Override
    public void newSpecial(int index, double price)
    {
    }
    
    @Override
    public void save() {
       model.save();
    }

    @Override
    public void getCurrentMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
