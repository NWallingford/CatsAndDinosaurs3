/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.exceptions.StorageException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cs414.a5.nwalling.models.IUserModel;
import cs414.a5.nwalling.models.UserModel;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 * @author Jacob
 */
public class CreateAccountController extends AbstractController implements ICreateAccountController, Callback<UserModel>{

    private IUserModel model;
    private IModelFactory modelFactory;
    private boolean nameFree = false;
    public CreateAccountController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.modelFactory = modelFactory;
        this.model = modelFactory.getEmptyIUserModel();
    }
    
    @Override
    public boolean usernameExists(String name) {
        try {
            source.validateUsername(name, this);
        } catch (StorageException ex) {
//            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean createUser() {
        //Do validation on fields here
//        return model.save();
    	
    	try {
			source.saveUser(model,new Callback<UserModel>() {

				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					//display error??
				}

				@Override
				public void onResponse(Response<UserModel> arg0, Retrofit arg1) {
					// Do nothing it worked
					
				}
				
			});
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    @Override
    public boolean validPassword() {
        return false;
    }
    
    @Override
    public void setUsername(String value) {
        model.setUsername(value);
    }

    @Override
    public void setPassword(String value) {
       model.setPassword(value);
    }

    @Override
    public void setFirstName(String value) {
        model.setFirstName(value);
    }

    @Override
    public void setLastName(String value) {
        model.setLastName(value);
    }

    @Override
    public void setAddress1(String value) {
        model.setAddress1(value);
    }

    @Override
    public void setAddress2(String value) {
        model.setAddress2(value);
    }

	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResponse(Response<UserModel> response, Retrofit arg1) {
		// TODO Auto-generated method stub
		nameFree = response.body() == null;
	}

//    public void submit(int creatorAuth){
//        if(creatorAuth > 0 & creatorAuth < 5)
//            (model).setAuthLevel(creatorAuth+1);
//        else
//            (model).setAuthLevel(5);
//        model.save();
//    }
}