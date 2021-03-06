/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.data;

import cs414.a5.nwalling.android.models.*;

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
         return new UserModel();
    }

    @Override
    public IMenuModel getEmptyIMenuModel() {
        return new MenuModel(this);
    }

    @Override
    public IChefModel getEmptyIChefModel() {
        return new ChefModel();
    }

    @Override
    public IItemModel getEmptyIItemModel() {
        return new ItemModel();
    }

    @Override
    public IOrderModel getEmptyIOrderModel() {
        return new OrderModel();
    }

    @Override
    public IPaymentModel getEmptyIPaymentModel() {
        return new PaymentModel(this);
    }
    
}
