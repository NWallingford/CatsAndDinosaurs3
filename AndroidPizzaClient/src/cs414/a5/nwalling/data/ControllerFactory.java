/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import cs414.a5.nwalling.controllers.*;

/**
 *
 * @author Jacob
 */
public class ControllerFactory implements IControllerFactory{

    private IModelFactory modelFactory;
    private IDataSource source;
    public ControllerFactory(IModelFactory modelFactory, IDataSource source)
    {
        this.modelFactory = modelFactory;
        this.source = source;
    }
    
    @Override
    public ILoginController getLoginController() {
        return new LoginController(modelFactory, source);
    }

    @Override
    public IChangeMenuController getChangeMenuController() {
        return new ChangeMenuController(modelFactory, source);
    }

    @Override
    public IChefController getChefController() {
        return new ChefController(modelFactory, source);
    }

    @Override
    public ICreateAccountController getCreateAccountController() {
        return new CreateAccountController(modelFactory,source);
    }

    @Override
    public IOrderController getOrderController() {
        return new OrderController(modelFactory,source);
    }

    @Override
    public IPaymentController getPaymentController() {
        return new PaymentController(modelFactory,source);
    }

    @Override
    public IEditAccountController getEditAccountController() {
        return new EditAccountController(modelFactory,source);
    }
    
}
