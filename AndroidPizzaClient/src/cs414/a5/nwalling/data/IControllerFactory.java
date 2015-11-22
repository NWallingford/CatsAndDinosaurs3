/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import cs414.a5.nwalling.controllers.IEditAccountController;
import cs414.a5.nwalling.controllers.IChangeMenuController;
import cs414.a5.nwalling.controllers.IChefController;
import cs414.a5.nwalling.controllers.ICreateAccountController;
import cs414.a5.nwalling.controllers.ILoginController;
import cs414.a5.nwalling.controllers.IOrderController;
import cs414.a5.nwalling.controllers.IPaymentController;

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
}
