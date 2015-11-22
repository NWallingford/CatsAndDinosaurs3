/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.Tester;

import cs414.a5.nwalling.controllers.ILoginController;
import cs414.a5.nwalling.controllers.LoginController;
import cs414.a5.nwalling.data.ControllerFactory;
import cs414.a5.nwalling.data.IControllerFactory;
import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.data.ModelFactory;
import cs414.a5.nwalling.data.PizzaServiceClient;
import cs414.a5.nwalling.exceptions.StorageException;
import java.util.ArrayList;
import cs414.a5.nwalling.models.IOrderModel;
import cs414.a5.nwalling.models.IPaymentModel;
import cs414.a5.nwalling.models.IUserModel;

/**
 *
 * @author Jacob
 */
public class FunctionalTester {
    public static void main(String[] args) throws StorageException
    {
        IDataSource source = new PizzaServiceClient("http://localhost:8080");
        IModelFactory m = new ModelFactory(source);
        IControllerFactory f = new ControllerFactory(m,source);
        IPaymentModel p= m.getEmptyIPaymentModel();
        p.setCardFirstName("dog");
        p.setCardLastName("doggingTon");
        p.setCardNumber(12);
        p.setCustomerAddress1("woof1");
        p.setCustomerAddress2("woof2");
        p.setCustomerCity("wan");
        p.setCustomerName("dog");
        p.setCustomerZip(1234);
        p.save();
//        ILoginController c = f.getLoginController();
//        ArrayList<IOrderModel> o = source.getOrders();
//        IUserModel u = source.getUser("Jbparker", "CATSANDDAWGSYO");
        
    }
}
