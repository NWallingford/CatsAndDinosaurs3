/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.IModelFactory;
import cs414.a5.nwalling.android.exceptions.StorageException;
import cs414.a5.nwalling.android.models.IUserModel;
import cs414.a5.nwalling.android.models.UserModel;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean usernameExists() {
        return !nameFree;
    }

    @Override
    public boolean createUser() {
        //Do validation on fields here
//        return model.save();
    	model.setAuthLevel(4);
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
    public boolean validPassword(String value) {
        return !(value.trim().equals(""));
        
    }
    
    @Override
    public boolean setUsername(String value) {
    	if(value.trim().equals(""))
    		return false;
    	try {
            source.validateUsername(value, this);
        } catch (StorageException ex) {
//            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.setUsername(value);
        return true;
    }

    @Override
    public boolean setPassword(String value) {
    	if(value.trim().equals(""))
    		return false;
       model.setPassword(value);
       return true;
    }

    @Override
    public boolean setFirstName(String value) {
    	if(value.trim().equals(""))
    		return false;
        model.setFirstName(value);
        return true;
    }

    @Override
    public boolean setLastName(String value) {
    	if(value.trim().equals(""))
    		return false;
        model.setLastName(value);
        return true;
    }

    @Override
    public boolean setAddress1(String value) {
    	if(value.trim().equals(""))
    		return false;
        model.setAddress1(value);
        return true;
    }

    @Override
    public boolean setAddress2(String value) {
        model.setAddress2(value);
        return true;
    }

	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResponse(Response<UserModel> response, Retrofit arg1) {
		// TODO Auto-generated method stub
		nameFree = response.body() == null;
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public boolean setEmailAddress(String value) {
    	if(value.trim().equals(""))
    		return false;
		// TODO Auto-generated method stub
		model.setEmailAddress(value);
		return true;
	}

	@Override
	public boolean setZip(String value) {
		// TODO Auto-generated method stub
		try
		{
		int tmp = Integer.parseInt(value);		
		model.setZip(tmp);
		}catch(Exception e)
		{
			return false;
		}

		return true;
	}

//    public void submit(int creatorAuth){
//        if(creatorAuth > 0 & creatorAuth < 5)
//            (model).setAuthLevel(creatorAuth+1);
//        else
//            (model).setAuthLevel(5);
//        model.save();
//    }
}