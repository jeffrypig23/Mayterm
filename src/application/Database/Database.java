package application.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import application.UI.Genre.genre;

public class Database {

	/**
	 * 
	 * @return Boolean based on whether or not the database exists on the system,
	 *         and if it can write to it.
	 */
	public static boolean databaseExist() {
		return (Environment.getFile().exists() && Environment.getFile().canWrite());
	}

	/**
	 * Attempts to create the database if it does not already exist on the system.
	 */
	public static void createDatabase() {
		JsonArray settings = Json.createArrayBuilder().build();
		JsonObject songs = Json.createObjectBuilder().build();
		JsonObject json = Json.createObjectBuilder().add("Settings", settings).add("Songs", songs).build();
		String result = json.toString();
		try {
			Environment.getFile().getParentFile().mkdirs();
			Environment.getFile().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		writeToDatabase(result);
	}

	/**
	 * Writes data to the database. It should be noted that this function will
	 * <b><i>overwrite</i></b> pre-existing data. <b>It does not append to the
	 * file</b>.
	 * 
	 * @param data
	 *            - The data that is going to be written to the file.
	 */
	public static void writeToDatabase(String data) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(Environment.getFile());
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Reads and returns the contents of the database.
	 * 
	 * @return The data from the database.
	 */
	public static String retrieveFromDatabase() {
		String data = null;
		FileReader reader = null;
		try {
			reader = new FileReader(Environment.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		try {
			data = bufferedReader.readLine();
			bufferedReader.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	/**
	 * Adds a song to the database.
	 * 
	 * @param path
	 *            - The file path of the song.
	 * @param genre
	 *            - The genre.
	 * @param title
	 *            - The title.
	 * @param artist
	 *            - The artist.
	 */
	public static void addSong(String path, genre genre, String title, String artist) {
		JsonObject song_info = Json.createObjectBuilder().add("Color", genre.toString()).add("Title", title)
				.add("Artist", artist).build();
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject full = read.readObject();
		read.close();
		JsonObject songs = full.getJsonObject("Songs");
		String songsArray = songs.toString();
		if (songsArray.isEmpty() || songsArray.equals("{}")) {
			songsArray = String.format("{\"%s\":%s}", path, song_info.toString());
		} else {
			songsArray = String.format("%s,\"%s\":%s}", songsArray.replace("}}", "}"), path, song_info.toString());
		}
		JsonReader newread = Json.createReader(new StringReader(songsArray));
		JsonObject newsong = Json.createObjectBuilder().add("Settings", full.get("Settings"))
				.add("Songs", newread.read()).build();
		newread.close();
		writeToDatabase(newsong.toString());
	}

	/**
	 * Returns if the provided path is in the database.
	 * 
	 * @param path
	 *            - The path of the song.
	 * @return Boolean based on whether or not the path is in the database.
	 */
	public static boolean isInDatabase(String path) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject fill = read.readObject();
		read.close();
		JsonObject songs = fill.getJsonObject("Songs");
		return songs.containsKey(path);
	}

	/**
	 * Gets the artist of a specific file from the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @return The artist.
	 */
	public static String getArtist(String path) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject fill = read.readObject();
		read.close();
		JsonObject songs = fill.getJsonObject("Songs");
		JsonObject specific_song = songs.getJsonObject(path);
		return specific_song.get("Artist").toString().replace("\"", "");
	}

	/**
	 * Gets the title of a specific file from the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @return The title.
	 */
	public static String getTitle(String path) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject fill = read.readObject();
		read.close();
		JsonObject songs = fill.getJsonObject("Songs");
		JsonObject specific_song = songs.getJsonObject(path);
		return specific_song.get("Title").toString().replace("\"", "");
	}

	/**
	 * Gets the genre of a specific file from the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @return The genre.
	 */
	public static String getGenre(String path) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject fill = read.readObject();
		read.close();
		JsonObject songs = fill.getJsonObject("Songs");
		JsonObject specific_song = songs.getJsonObject(path);
		return specific_song.get("Color").toString().replace("\"", "");
	}

	/**
	 * Overrides and edits the artist in the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @param newArtist
	 *            The new artist name.
	 */
	public static void editArtist(String path, String newArtist) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject full = read.readObject();
		read.close();
		JsonObject songs = full.getJsonObject("Songs");
		JsonObject song_info = Json.createObjectBuilder().add("Color", getGenre(path)).add("Title", getTitle(path))
				.add("Artist", newArtist.toString()).build();
		String songsArray = songs.toString();
		if (songsArray.isEmpty() || songsArray.equals("{}")) {
			songsArray = String.format("{\"%s\":%s}", path, song_info.toString());
		} else {
			songsArray = String.format("%s,\"%s\":%s}", songsArray.replace("}}", "}"), path, song_info.toString());
		}
		JsonReader newread = Json.createReader(new StringReader(songsArray));
		JsonObject newValue = Json.createObjectBuilder().add("Settings", full.get("Settings"))
				.add("Songs", newread.read()).build();
		newread.close();
		writeToDatabase(newValue.toString());
	}

	/**
	 * Overrides and edits the title in the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @param newTitle
	 *            The new title name.
	 */
	public static void editTitle(String path, String newTitle) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject full = read.readObject();
		read.close();
		JsonObject songs = full.getJsonObject("Songs");
		JsonObject song_info = Json.createObjectBuilder().add("Color", getGenre(path)).add("Title", newTitle.toString())
				.add("Artist", getArtist(path)).build();
		String songsArray = songs.toString();
		if (songsArray.isEmpty() || songsArray.equals("{}")) {
			songsArray = String.format("{\"%s\":%s}", path, song_info.toString());
		} else {
			songsArray = String.format("%s,\"%s\":%s}", songsArray.replace("}}", "}"), path, song_info.toString());
		}
		JsonReader newread = Json.createReader(new StringReader(songsArray));
		JsonObject newValue = Json.createObjectBuilder().add("Settings", full.get("Settings"))
				.add("Songs", newread.read()).build();
		newread.close();
		writeToDatabase(newValue.toString());
	}

	/**
	 * Overrides and edits the genre in the database.
	 * 
	 * @param path
	 *            - Path of the file that is in the database.
	 * @param newGenre
	 *            The new genre.
	 */
	public static void editGenre(String path, genre newGenre) {
		JsonReader read = Json.createReader(new StringReader(retrieveFromDatabase()));
		JsonObject full = read.readObject();
		read.close();
		JsonObject songs = full.getJsonObject("Songs");
		JsonObject song_info = Json.createObjectBuilder().add("Color", newGenre.toString()).add("Title", getTitle(path))
				.add("Artist", getArtist(path)).build();
		String songsArray = songs.toString();
		if (songsArray.isEmpty() || songsArray.equals("{}")) {
			songsArray = String.format("{\"%s\":%s}", path, song_info.toString());
		} else {
			songsArray = String.format("%s,\"%s\":%s}", songsArray.replace("}}", "}"), path, song_info.toString());
		}
		JsonReader newread = Json.createReader(new StringReader(songsArray));
		JsonObject newValue = Json.createObjectBuilder().add("Settings", full.get("Settings"))
				.add("Songs", newread.read()).build();
		newread.close();
		writeToDatabase(newValue.toString());
	}

}
