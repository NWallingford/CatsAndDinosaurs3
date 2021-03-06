/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.data;

import cs414.a5.nwalling.android.controllers.*;

/**
 *
 * @author Jacob
 */
public interface IControllerFactory {
    ILoginController getLoginController();
    IChangeMenuController getChangeMenuController();
    IChefController getChefController();
    ICreateAccountController getCreateAccountController();
    IOrderController getOrderController();
    IPaymentController getPaymentController();
    IEditAccountController getEditAccountController();
    IMenuController getMenuController();
}
