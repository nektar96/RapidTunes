/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2016 The RapidTunes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.oskarmendel.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.oskarmendel.model.SearchResultModel;
import me.oskarmendel.player.search.YouTubeSearch;
import me.oskarmendel.util.DoublyLinkedList;

/**
 * Controller class for the navigation menu of the application.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name NavigationController.java
 */
public class NavigationController implements RapidTunesController {
	
	@FXML private Button navBackBtn;
	@FXML private Button navFrontBtn;
	@FXML private TextField navSearchField;
	@FXML private Button navSearchBtn;
	
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getName());
	
	private SearchResultModel searchResultModel;
	
	DoublyLinkedList<String> searchHistory = new DoublyLinkedList<String>();
	DoublyLinkedList<String>.DoublyLinkedListIterator searchHistoryIterator;
	int searchIterator = 0;
	
	/**
	 * Initialize the navigation controller. 
	 * TODO Fix out of bounds exception when scrolling search terms.
	 * TODO Implement YouTube search functionality.
	 * TODO Migrate code to store history to its own class.
	 */
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		/* TEMPORARY CODE FOR TESTING STACKED LIST. */
		DoublyLinkedList<String> searchH = new DoublyLinkedList<String>();
	    searchH.addFirst("Test1");
		searchH.addFirst("Test2");
		searchH.addFirst("Test3");
 		searchH.add("Test4");
 		searchH.add("Test5");
 		searchH.add("Test6");
 		searchH.addFirst("Test7");
	 	searchH.displayList();
	 	System.out.println("Popped element with text: " + searchH.removeFirst());
	 	System.out.println("Popped element with text: " + searchH.removeFirst());
	 	//System.out.println("Popped element with text: " + searchH.remove());
	 	//searchH.displayList();
	 	
	 	searchH.add("Lego");
	 	searchH.contains(123);
	 	searchH.contains("Lego");
		
	 	//searchH.remove("Test4");
	 	//searchH.remove("Test6");
	 	//searchH.remove("Test2");
	 	//searchH.remove("Test1");
	 	//searchH.remove("Lego");
	 	searchH.displayList();
	 	
	 	//System.out.println(searchH.get(5));
		
		searchHistoryIterator = searchHistory.getIterator(true);
		YouTubeSearch youtubeSearch = new YouTubeSearch();
		
		navBackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e){
				if (searchHistoryIterator.hasPrev()) {
					navSearchField.setText(searchHistoryIterator.prev());
				}
			}
		});
		
		navFrontBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e){
				if (searchHistoryIterator.hasNext()) {
					navSearchField.setText(searchHistoryIterator.next());
				}
			}
		});
		
		navSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        //Save the search to history so we can back to it later.
		        searchHistory.add(navSearchField.getText());
		        searchHistoryIterator = searchHistory.getIterator(true);
		        
		        //Performs the search for the keywords in the YouTube data API.
		        //System.out.println(youtubeSearch.search(navSearchField.getText()));
		        //stageManager.getSongBrowserController().populateSongBrowser(youtubeSearch.search(navSearchField.getText()));
		        searchResultModel.setSearchResultList(youtubeSearch.search(navSearchField.getText()));
		    }
		});
	}
	
	/**
	 * 
	 * @param searchResultModel
	 */
	public void initSearchResultModel(SearchResultModel searchResultModel) {
		//Make sure model is only set once.
		if (this.searchResultModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.searchResultModel = searchResultModel;
		
		//This controller will edit the SearchResultModel
		
	}
}
