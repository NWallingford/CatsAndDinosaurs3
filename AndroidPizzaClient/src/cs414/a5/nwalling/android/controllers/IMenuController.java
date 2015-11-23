package cs414.a5.nwalling.android.controllers;

import java.util.ArrayList;

public interface IMenuController extends IController{

	void fetchMenu();

	ArrayList<String> getMenu();

}
