/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import cs414.a5.nwalling.models.*;

/**
 *
 * @author Jacob
 */
public class SqliteDataSource implements IDataSource{
    
    private String connectionString;
    private Connection connection;
    private IModelFactory modelFactory;
    public SqliteDataSource(String connectionString)
    {
        this.modelFactory = new ModelFactory(null);
        this.connectionString = connectionString;
    }
    
    @Override
    public void load() throws LoadException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connectionString);
        }
        catch(SQLException e)
        {
            throw new LoadException("Failure to load sqlConnection!", e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqliteDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private <K extends AbstractModel> IModel executeQuery(Class<K> modelClass,String query) throws StorageException
    {   
        try (Statement cmd = connection.createStatement()) {
            K model = modelClass.newInstance();
            HashMap<String,Object> fields = new HashMap<>();
            ResultSet results = cmd.executeQuery(query);
            //grab only the first one.
            
            if(results.next())
            {
                fields = translateItem(results);
                model.load(fields);
                return model;
            }
            return null;
        }
        catch(SQLException e)
        {
            throw new StorageException("Failed to fetch Item!", e);
        }
        catch(LoadException e)
        {
            throw new StorageException("Failed to translate data!", e);
        } 
        catch (InstantiationException e) 
        {
            throw new StorageException("Failed to instantiate provided Type! Type: " + modelClass.toString(), e);
        } 
        catch (IllegalAccessException e) 
        {
            throw new StorageException("\"Failed to instantiate provided Type! Type: " + modelClass.toString(), e);
        }
        catch(Exception e)
        {
            throw new StorageException("Something went horribly wrong!");
        }
    }
    private <K extends AbstractModel> ArrayList<IModel> executeQueryMultiple (Class<K> modelClass, String query) throws StorageException
    {
        try (Statement cmd = connection.createStatement()) {
            ArrayList<IModel> rtn = new ArrayList<>();
            
            ResultSet results = cmd.executeQuery(query);
            while(results.next())
            {
                K model = modelClass.newInstance();
                HashMap<String,Object> fields = new HashMap<>();
                fields = translateItem(results);
                model.load(fields);
                rtn.add(model);
            }
            return rtn;
        }
        catch(SQLException e)
        {
            throw new StorageException("Failed to fetch Item!", e);
        }
        catch(LoadException e)
        {
            throw new StorageException("Failed to translate data!", e);
        } 
        catch (InstantiationException e) 
        {
            throw new StorageException("Failed to instantiate provided Type! Type: " + modelClass.toString(), e);
        } 
        catch (IllegalAccessException e) 
        {
            throw new StorageException("\"Failed to instantiate provided Type! Type: " + modelClass.toString(), e);
        }
        catch(Exception e)
        {
            throw new StorageException("Something went horribly wrong!");
        }
    }
    private HashMap<String,Object> translateItem(ResultSet results) throws SQLException
    {
        HashMap<String,Object> fields = new HashMap<>();
        
        ResultSetMetaData mt = results.getMetaData();
        for(int i = 1; i < mt.getColumnCount()+1; i++)
        {
            if(results.getObject(i) != null)
                fields.put(mt.getColumnName(i).toLowerCase(), results.getObject(i));
        }
        return fields;
    }

    private void executeNonQuery(String query) throws StorageException
    {
        try (Statement cmd = connection.createStatement()) {
            cmd.execute(query);
        }
        catch(SQLException e)
        {
            throw new StorageException("Failed to fetch Item!", e);
        }
        catch(Exception e)
        {
            throw new StorageException("Something went horribly wrong!");
        }
    }
   
    @Override
    public IItemModel getItem(int id) throws StorageException
    {
        StringBuilder query = new StringBuilder("SELECT * FROM Items WHERE Id = '");
        query.append(id);
        query.append("' AND IsActive = 'true'");
        IItemModel rtn = (IItemModel)executeQuery(ItemModel.class,query.toString());
        return rtn;
    }
    
    @Override
    public ArrayList<IItemModel> getItems() throws StorageException{
        String query = "SELECT * FROM Items WHERE Type > 3 AND IsActive = 'true'";
        ArrayList<IModel> rtn = executeQueryMultiple(ItemModel.class, query);
        ArrayList<IItemModel> tmp = new ArrayList<>();
        rtn.stream().forEach((x) -> { tmp.add((IItemModel)x);});
        return tmp;
    }
    
    @Override
    public IMenuModel getMenu() throws StorageException {
        String query = "SELECT * FROM Items WHERE Type > 3 AND IsActive = 'true'";
        
        ArrayList<IModel> items = executeQueryMultiple(ItemModel.class, query);
        ArrayList<IItemModel> tmp = new ArrayList<>();
        items.stream().forEach((x) -> {tmp.add((IItemModel)x);});
        IMenuModel rtn = modelFactory.getEmptyIMenuModel();
        (rtn).setItems(tmp);
        
        return rtn;
    }

    @Override
    public IOrderModel getOrder(int id) throws StorageException {
        StringBuilder query = new StringBuilder("SELECT * FROM Orders WHERE id = '");
        query.append(id).append("'");
        
        IOrderModel rtn = (IOrderModel)executeQuery(OrderModel.class, query.toString());
        
        if(rtn == null)
            return null;
                
        StringBuilder itemQuery = new StringBuilder("SELECT * FROM Items as i JOIN ItemOrder as o ON o.ItemId = i.id WHERE o.OrderId = '");
        itemQuery.append(((OrderModel)rtn).getId());
        itemQuery.append("'");
        
        ArrayList<IModel> items = executeQueryMultiple(ItemModel.class,itemQuery.toString());
        ArrayList<IItemModel> tmp = new ArrayList<>();
        items.stream().forEach((x) -> {tmp.add((IItemModel)x);});            
        (rtn).setItems(tmp);
        
        return rtn;
    }
    @Override
    public ArrayList<IOrderModel> getOrders() throws StorageException {
        String query = "SELECT * FROM Orders";
        
        ArrayList<IModel> orders = executeQueryMultiple(OrderModel.class, query);
        ArrayList<IOrderModel> rtn = new ArrayList<>();
        orders.stream().forEach((x)-> {rtn.add((IOrderModel)x);});
        for(IOrderModel order : rtn)
        {
            StringBuilder itemQuery = new StringBuilder("SELECT * FROM Items as i JOIN ItemOrder as o ON o.ItemId = i.id WHERE o.OrderId = '");
            itemQuery.append(((OrderModel)order).getId());
            itemQuery.append("'");
        
            ArrayList<IModel> items = executeQueryMultiple(ItemModel.class,itemQuery.toString());
            ArrayList<IItemModel> tmp = new ArrayList<>();
            items.stream().forEach((x) -> {tmp.add((IItemModel)x);});            
            (order).setItems(tmp);
        }        
        return rtn;
    }

    @Override
    public ArrayList<IOrderModel> getOrders(int id) throws StorageException {
//        StringBuilder query = new StringBuilder("SELECT * FROM Orders WHERE UserId = '");
//        query.append(id);
//        query.append("' AND StatusId < 4");
//        
//        ArrayList<IModel> orders = executeQueryMultiple(OrderModel.class, query.toString());
//        
////        for(IModel model : orders)
////        {
////            StringBuilder itemQuery = new StringBuilder("SELECT * FROM Items as i JOIN ItemOrder as o ON o.ItemId = i.id WHERE o.OrderId = '");
////            itemQuery.append(((OrderModel)model).getId());
////            itemQuery.append("'");
//        
////            ObservableList<IModel> items = executeQueryMultiple(ItemModel.class,itemQuery.toString());
////            ((OrderModel)model).setItems(FXCollections.observableArrayList(items));
////        }
//        
//        return orders;
        return null;
    }

    @Override
    public ArrayList<IUserModel> getUsers() throws StorageException {
        String query = "SELECT * FROM Users";
        
        ArrayList<IModel> results = executeQueryMultiple(UserModel.class, query);
        ArrayList<IUserModel> rtn = new ArrayList<>();
        results.stream().forEach((x) -> {rtn.add((IUserModel)x);});
        return rtn;
    }
    
    @Override
    public IUserModel getUser(int id) throws StorageException {
        StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE Id = '");
        query.append(id).append("'");
        
        IModel rtn = executeQuery(UserModel.class,query.toString());
        return (IUserModel)rtn;
    }

    @Override
    public IUserModel getUser(String userName, String password) throws StorageException {
        StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE UserName = '");
        query.append(userName).append("' AND Password = '");
        query.append(password).append("'");
        
        IModel rtn = executeQuery(UserModel.class,query.toString());
       
        return (IUserModel)rtn;
    }

    
    @Override
    public boolean validateUsername(String name) throws StorageException {
        StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE UserName = '");
        query.append(name).append("'");

        IModel rtn = executeQuery(UserModel.class,query.toString());
        return rtn == null;
    }
    
    @Override
    public void saveItem(IItemModel model) throws StorageException {
        StringBuilder query = new StringBuilder();
        if(model.getId() < 1)
        {
            query.append("INSERT INTO Items (Name,Description,Price,SpecialPrice,IsSpecial,IsActive) VALUES ('");
            query.append(model.getName()).append("', '");
            query.append(model.getDescription()).append("', '");
            query.append(model.getPrice()).append("', '");
            query.append(model.getSpecialPrice()).append("', '");
            query.append(model.getIsSpecial()).append("', '");
            query.append(model.getIsActive()).append("')");
        }
        else
        {
            query.append("UPDATE Items  SET Name = '");
            query.append(model.getName()).append("', Description = '");
            query.append(model.getDescription()).append("', Price = '");
            query.append(model.getPrice()).append("', IsSpecial = '");
            query.append(model.getIsSpecial()).append("', SpecialPrice = '");
            query.append(model.getSpecialPrice()).append("', IsSpecial = '");
            query.append(model.getIsSpecial()).append("', IsActive = '");
            query.append(model.getIsActive()).append("' WHERE Id = '");
            query.append(model.getId()).append("'");
        }
       
        executeNonQuery(query.toString());
    }

    @Override
    public void saveMenu(IMenuModel model) throws StorageException {
        MenuModel m = (MenuModel)model;
        
        for(int i = 0; i< m.getItems().size(); i++)
        {
            IItemModel n = (IItemModel)m.getItems().get(i);
            saveItem(n);
        }
    }

    @Override
    public void saveOrder(IOrderModel m) throws StorageException {
        StringBuilder query = new StringBuilder();
        if(m.getId() < 1)
        {
            query.append("INSERT INTO Orders (UserId,StatusId,CustomerName,CustomerCity,CustomerAddress1,CustomerAddress2,CustomerZip) VALUES ('");
            query.append(m.getUser()).append("', '");
            query.append(m.getStatus()).append("', '");
            query.append(m.getCustomerName()).append("', '");
            query.append(m.getCustomerCity()).append("', '");
            query.append(m.getCustomerAddress1()).append("', '");
            query.append(m.getCustomerAddress2()).append("', '");
            query.append(m.getCustomerZip()).append("')");
        }
        else
        {
            query.append("UPDATE Orders SET UserId = '");
            query.append(m.getUser()).append("', StatusId = '");
            query.append(m.getStatus()).append("', CustomerName = '");
            query.append(m.getCustomerName()).append("', CustomerCity = '");
            query.append(m.getCustomerCity()).append("', CustomerAddress1 = '");
            query.append(m.getCustomerAddress1()).append("', CustomerAddress2 = '");
            query.append(m.getCustomerAddress2()).append("', CustomerZip = '");
            query.append(m.getCustomerZip()).append("' WHERE Id = '");
            query.append(m.getId()).append("'");
        }
       
        executeNonQuery(query.toString());
        if(m.getItems().size() > 0)
        {
            String q = "SELECT Id FROM Orders ORDER BY Id DESC";

            ArrayList<IModel> orders = executeQueryMultiple(OrderModel.class, q);
            int id = ((OrderModel)orders.get(0)).getId();

            StringBuilder q2 = new StringBuilder();
            q2.append("INSERT INTO ItemOrder (OrderId,ItemId) VALUES");
            for(int i = 0; i < m.getItems().size(); i++)
            {
                q2.append("(").append(id).append(", ").append(m.getItems().get(i).getId()).append(")");
                if(i < m.getItems().size()-1)
                    q2.append(",");
            }
            executeNonQuery(q2.toString());
        }
    }

    @Override
    public void saveUser(IUserModel model) throws StorageException {
        UserModel m = (UserModel)model;
        StringBuilder query = new StringBuilder();
        if(m.getId() < 1)
        {
            query.append("INSERT INTO Users (UserName,Password,FirstName,LastName,EmailAddress,Address1,Address2,City,Zip,AuthLevelId) VALUES ('");
            query.append(m.getUsername()).append("', '");
            query.append(m.getPassword()).append("', '");
            query.append(m.getFirstName()).append("', '");
            query.append(m.getLastName()).append("', '");
            query.append(m.getEmailAddress()).append("', '");
            query.append(m.getAddress1()).append("', '");
            query.append(m.getAddress2()).append("', '");
            query.append(m.getCity()).append("', '");
            query.append(m.getZip()).append("', '");
            query.append(m.getAuthLevel()).append("')");
        }
        else
        {
            query.append("UPDATE Users  SET UserName = '");
            query.append(m.getUsername()).append("', Password = '");
            query.append(m.getPassword()).append("', FirstName = '");
            query.append(m.getFirstName()).append("', EmailAddress = '");
            query.append(m.getEmailAddress()).append("', LastName = '");
            query.append(m.getLastName()).append("', Address1 = '");
            query.append(m.getAddress1()).append("', Address2 = '");
            query.append(m.getAddress2()).append("', AuthLevelId = '");
            query.append(m.getAuthLevel()).append("', City = '");
            query.append(m.getCity()).append("', Zip = '");
            query.append(m.getZip()).append("' WHERE Id = '");
            query.append(m.getId()).append("'");
        }
       
        executeNonQuery(query.toString());
    }
    
    @Override
    public void savePayment(IPaymentModel model) throws StorageException {
        PaymentModel m = (PaymentModel)model;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Payment (CardHolderFirstName,CardHolderLastName,CardNumber,ExpMonth,ExpYear,Ammount) VALUES ('");
        query.append(m.getCardFirstName()).append("', '");
        query.append(m.getCardLastName()).append("', '");
        query.append(m.getCardNumber()).append("', '");
        query.append(m.getExpMonth()).append("', '");
        query.append(m.getExpYear()).append("', '");
        query.append(m.getTotal()).append("')");
        
        executeNonQuery(query.toString());
    }


}
