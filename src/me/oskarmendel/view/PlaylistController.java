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

import org.controlsfx.glyphfont.Glyph;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oskarmendel.model.CurrentlyPlayingModel;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name PlaylistController.java
 */
public class PlaylistController implements RapidTunesController {
	
	@FXML private Glyph playlistExploreIco;
	@FXML private Button playlistNewBtn;
	@FXML private Glyph playlistNewBtnIco;
	@FXML private ImageView playlistImg;
	
	private static final Logger LOGGER = Logger.getLogger(PlaylistController.class.getName());
	
	private CurrentlyPlayingModel currentlyPlayingModel;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		this.playlistExploreIco.size(25);
		this.playlistNewBtnIco.size(15);
		
		this.playlistNewBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e){
				System.out.println("New Playlist");
			}
		});
	}
	
	/**
	 * Initializes the CurrentlyPlayingModel which this class will get data from when a song is 
	 * clicked within the SongBrowser and this class will display the title and artist for the song. 
	 * 
	 * @param currentlyPlayingModel - currentlyPlayingModel object to send data to.
	 */
	public void initCurrentlyPlayingModel(CurrentlyPlayingModel currentlyPlayingModel) {
		//Make sure model is only set once.
		if (this.currentlyPlayingModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.currentlyPlayingModel = currentlyPlayingModel;
		
		// Bind the playlistImage to the current thumbnail value.
		currentlyPlayingModel.getCurrentSongThumbnail().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				playlistImg.setImage(new Image(newValue));
			}
			
		});
	}
}
