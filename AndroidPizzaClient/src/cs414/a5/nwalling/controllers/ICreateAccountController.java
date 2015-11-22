/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

/**
 *
 * @author Jacob
 */
public interface ICreateAccountController extends IController {
    boolean usernameExists(String name);
    boolean createUser();
    boolean validPassword();
    void setUsername(String value);
    void setPassword(String value);
    void setFirstName(String value);
    void setLastName(String value);
    void setAddress1(String value);
    void setAddress2(String value);
    
}
