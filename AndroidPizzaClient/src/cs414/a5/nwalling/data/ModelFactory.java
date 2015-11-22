/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import cs414.a5.nwalling.models.*;

/**
 *
 * @author Jacob
 */
public class ModelFactory implements IModelFactory {

    private IDataSource source;
    public ModelFactory(IDataSource source)
    {
        this.source = source;
    }
    
    @Override
    public IUserModel getEmptyIUserModel() {
         return new UserModel(source);
    }

    @Override
    public IMenuModel getEmptyIMenuModel() {
        return new MenuModel(this, source);
    }

    @Override
    public IChefModel getEmptyIChefModel() {
        return new ChefModel(source);
    }

    @Override
    public IItemModel getEmptyIItemModel() {
        return new ItemModel(source);
    }

    @Override
    public IOrderModel getEmptyIOrderModel() {
        return new OrderModel(source);
    }

    @Override
    public IPaymentModel getEmptyIPaymentModel() {
        return new PaymentModel(this, source);
    }
    
}
