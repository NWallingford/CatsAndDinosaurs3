package cs414.a5.nwalling.controllers;

import java.util.ArrayList;

public interface IMenuController extends IController{

	void fetchMenu();

	ArrayList<String> getMenu();

}
