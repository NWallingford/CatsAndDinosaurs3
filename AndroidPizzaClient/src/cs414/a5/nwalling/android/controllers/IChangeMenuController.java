/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

import java.util.ArrayList;

import cs414.a5.nwalling.android.models.IItemModel;

/**
 *
 * @author Jacob
 */
public interface IChangeMenuController extends IController {
	ArrayList<IItemModel> getMenu();
    void fetchCurrentMenu();
    void removeItems(int[] indices);
    void newItem(String name, double price);
    void revokeSpecial(int[] indices);
    void newSpecial(int index, double price);
    void save();
}
