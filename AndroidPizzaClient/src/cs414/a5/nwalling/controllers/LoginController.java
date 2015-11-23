/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.models.IUserModel;
import cs414.a5.nwalling.models.UserModel;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 * @author Jacob
 */
public class LoginController extends AbstractController implements ILoginController, Callback<UserModel> {

    private IUserModel model;
    private IModelFactory modelFactory;
    public LoginController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.modelFactory = modelFactory;
        this.model = modelFactory.getEmptyIUserModel();
    }
    
    @Override
    public void login() {
        try
        {
            //Will return null if no user was found with the given username and password.
            source.getUser(model.getUsername(), model.getPassword(), this);
            //Insert local login logic here.
            
            //End Local Login Logic
        }
        catch(Exception e)
        {
            //Should never throw an exception but if it does you need to do something with it.
            //Display an error or something or log the errror somehow
        }
    }   
    
    //This method is so that you can set the username and password into the model when it's provided
    //I would suggest calling these methods on focus lost of their respective text boxes.
    @Override
    public void setUsername(String value) {
        model.setUsername(value);
    }

    @Override
    public void setPassword(String value) {
        model.setPassword(value);
    }

	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		//display error
	}

	@Override
	public void onResponse(Response<UserModel> response, Retrofit arg1) {
		model = response.body();
		this.setChanged();
		this.notifyObservers();
	}
}
