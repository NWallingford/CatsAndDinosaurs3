/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

/**
 *
 * @author Jacob
 */
public interface ICreateAccountController extends IController {
	
    boolean usernameExists();
    boolean createUser();
    boolean validPassword(String value);
    boolean setUsername(String value);
    boolean setPassword(String value);
    boolean setFirstName(String value);
    boolean setLastName(String value);
    boolean setAddress1(String value);
    boolean setAddress2(String value);
    boolean setEmailAddress(String value);
    boolean setZip(String value);
    
}
