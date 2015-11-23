/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.data;

import cs414.a5.nwalling.android.models.IChefModel;
import cs414.a5.nwalling.android.models.IItemModel;
import cs414.a5.nwalling.android.models.IMenuModel;
import cs414.a5.nwalling.android.models.IOrderModel;
import cs414.a5.nwalling.android.models.IPaymentModel;
import cs414.a5.nwalling.android.models.IUserModel;

/**
 *
 * @author Jacob
 */
public interface IModelFactory {
    IUserModel getEmptyIUserModel();
    IMenuModel getEmptyIMenuModel();
    IChefModel getEmptyIChefModel();
    IItemModel getEmptyIItemModel();
    IOrderModel getEmptyIOrderModel();
    IPaymentModel getEmptyIPaymentModel();
    
}
