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
public interface IChangeMenuController extends IController {
    void getCurrentMenu();
    void removeItems(int[] indices);
    void newItem(String name, double price);
    void revokeSpecial(int[] indices);
    void newSpecial(int index, double price);
    void save();
}
