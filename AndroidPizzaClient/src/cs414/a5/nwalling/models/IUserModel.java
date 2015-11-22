/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

/**
 *
 * @author Jacob
 */
public interface IUserModel extends IModel{
    String getUsername();
    void setUsername(String value);
    String getPassword();
    void setPassword(String value);
    String getFirstName();
    void setFirstName(String value);
    String getLastName();
    void setLastName(String value);
    String getEmailAddress();
    void setEmailAddress(String value);
    String getAddress1();
    void setAddress1(String value);
    String getAddress2();
    void setAddress2(String value);
    String getCity();
    void setCity(String value);
    int getZip();
    void setZip(int value);
    int getAuthLevel();
    void setAuthLevel(int value);
}
