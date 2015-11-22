/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import cs414.a5.nwalling.models.*;

/**
 * Jersey REST client generated for REST resource:PizzaService
 * [PizzaService]<br>
 * USAGE:
 * <pre>
 *        PizzaServiceClient client = new PizzaServiceClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jacob
 */
public class PizzaServiceClient implements IDataSource {
    private WebTarget webTarget;
    private Client client;
    private Gson gson;
    private static final String BASE_URI = "PizzaWebService/webresources";

    public PizzaServiceClient(String host) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(host+"/"+BASE_URI).path("PizzaService");
        gson = new Gson();
    }
    
    @Override
    public void saveItem(IItemModel requestEntity) throws ClientErrorException, StorageException {
        webTarget.path("Save/Item").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(gson.toJson(requestEntity), javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public void saveOrder(IOrderModel requestEntity) throws ClientErrorException, StorageException {
        webTarget.path("Save/Order").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public UserModel getUser(int id) throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/User/{0}", new Object[]{id}));
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        return gson.fromJson(json, UserModel.class);
    }
    
    @Override
    public UserModel getUser(String username, String password) throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/User/{0}/{1}", new Object[]{username, password}));
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        if(json == null)
        {
            UserModel m = new UserModel(null);
            m.setId(-1);
            return m;
        }
        return gson.fromJson(json, UserModel.class);
    }

    @Override
    public ArrayList<IUserModel> getUsers() throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path("Get/Users");
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        ArrayList<IUserModel> models = gson.fromJson(resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class), new TypeToken<ArrayList<UserModel>>(){}.getType());
        return models;
    } 

    @Override
    public ItemModel getItem(int id) throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/Item/{0}", new Object[]{id}));
        
        String json =  resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        ItemModel model = gson.fromJson(json, ItemModel.class);
        return model;
    }

    @Override
    public ArrayList<IOrderModel> getOrders() throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path("Get/Orders");
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        ArrayList<IOrderModel> models = gson.fromJson(json, new TypeToken<ArrayList<OrderModel>>(){}.getType());
        return models;
    }

    @Override
    public ArrayList<IOrderModel> getOrders(int id) throws StorageException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/Orders/{0}",new Object[] {id}));
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        ArrayList<IOrderModel> models = gson.fromJson(json, new TypeToken<ArrayList<OrderModel>>(){}.getType());
        return models;
    }
    @Override
    public void saveMenu(IMenuModel requestEntity) throws ClientErrorException, StorageException {
        
        String json = gson.toJson(requestEntity.getItems());
        
        webTarget.path("Save/Menu").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(json, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public ArrayList<IItemModel> getItems() throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path("Get/Items");
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        ArrayList<IItemModel> models = gson.fromJson(json, new TypeToken<ArrayList<ItemModel>>(){}.getType());
        return models;
    }

    @Override
    public IOrderModel getOrder(int id) throws ClientErrorException, StorageException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/Order/{0}", new Object[]{id}));
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        return gson.fromJson(json, OrderModel.class);
    }

    @Override
    public IMenuModel getMenu() throws ClientErrorException, StorageException {
        
        ArrayList<IItemModel> items = getItems();
        IMenuModel rtn = new MenuModel();
        rtn.setItems(items);
        return rtn;
    }
    
    @Override
    public void saveUser(IUserModel requestEntity) throws ClientErrorException, StorageException {
        webTarget.path("Save/User").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }
    
    @Override
    public void load() throws LoadException {
        
    }


    @Override
    public void savePayment(IPaymentModel requestEntity) throws ClientErrorException, StorageException {
        webTarget.path("Save/Payment").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public boolean validateUsername(String name) {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Get/User/{0}", new Object[]{name}));
        String json = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        boolean rtn = gson.fromJson(json, Boolean.class);
        return rtn;
    }

    

}
