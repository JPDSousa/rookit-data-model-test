/*******************************************************************************
 * Copyright (C) 2017 Joao Sousa
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.rookit.dm.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.commons.text.RandomStringGenerator;
import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.factory.RookitFactories;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.play.Playlist;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TypeTrack;
import org.rookit.api.dm.track.TypeVersion;

import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;

@SuppressWarnings("javadoc")
public final class DMTestFactory {

	public static final Path TEST_RESOURCE = Paths.get("testStore");
	public static final Path TRACK_RESOURCE = TEST_RESOURCE.resolve("tracks").resolve("unparsed");
	public static final Path FORMATS = TRACK_RESOURCE.getParent().resolve("testFormats");
	private static final int RANDOM_LENGTH = 999999999;
	
	public static AbstractModule getModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(DMTestFactory.class);
			}
		};
	}
	
	private final RandomStringGenerator randomStringGenerator;
	private final RookitFactories factories;
	
	@Inject
	private DMTestFactory(RookitFactories factories){
		this.factories = factories;
		randomStringGenerator = new RandomStringGenerator.Builder()
				.withinRange('a', 'z')
				.build();
	}
	
	public RookitFactories getFactories() {
		return factories;
	}

	private final <T> T getUnique(T item, Supplier<T> supplier) {
		T another;
		do {
			another = supplier.get();
		} while(item.equals(another));
		return another;
	}

	public final Track getRandomOriginalTrack() {
		final String title = randomString();
		return getRandomTrack(title);
	}
	
	public final Track getRandomUniqueOriginalTrack(Track track) {
		return getUnique(track, () -> getRandomOriginalTrack());
	}
	
	public final Track getRandomTrack(String title) {
		final Set<Artist> mainArtists = getRandomSetOfArtists();
		final Set<Artist> features = Sets.newLinkedHashSet();
		final Set<Genre> genres = getRandomSetOfGenres();
		
		return createOriginalTrack(title, mainArtists, features, genres);
	}
	
	public final Track getRandomUniqueTrack(String title, Track track) {
		return getUnique(track, () -> getRandomTrack(title));
	}
	
	public final Track getRandomTrack(TypeTrack type) {
		final String title = randomString();
		final Track original = getRandomOriginalTrack();
		final TypeVersion versionType = TypeVersion.EXTENDED;
		return factories.getTrackFactory().createTrack(type, title, original, versionType);	
	}
	
	public final Track getRandomUniqueTrack(TypeTrack type, Track track) {
		return getUnique(track, () -> getRandomTrack(type));
	}
	
	private Track createOriginalTrack(String title, Set<Artist> mainArtists, Set<Artist> features, Set<Genre> genres) {
		final Track track = factories.getTrackFactory().createOriginalTrack(title);
		track.setMainArtists(mainArtists);
		track.setFeatures(features);
		track.setGenres(genres);
		
		return track;
	}

	public Set<Track> getRandomSetOfTracks(){
		Set<Track> tracks = Sets.newLinkedHashSet();
		Random random = new Random();
		for(int i=0; i<random.nextInt(19)+1; i++){
			tracks.add(getRandomOriginalTrack());
		}
		return tracks;
	}
	
	public Set<Track> getRandomUniqueSetOfTracks(Set<Track> tracks) {
		return getUnique(tracks, () -> getRandomSetOfTracks());
	}

	public Album getRandomAlbum(){
		Random random = new Random();
		final TypeRelease type = TypeRelease.values()[random.nextInt(TypeRelease.values().length)];	
		final String title = "AlbumTest"+random.nextInt(RANDOM_LENGTH);
		final Set<Artist> artists = getRandomSetOfArtists();
		
		return factories.getAlbumFactory().createSingleArtistAlbum(title, type, artists);
	}
	
	public Album getRandomUniqueAlbum(Album album) {
		return getUnique(album, () -> getRandomAlbum());
	}

	public Set<Album> getRandomSetOfAlbums(){
		Set<Album> albums = Sets.newLinkedHashSet();
		Random random = new Random();
		for(int i=0; i<random.nextInt(19)+1; i++){
			albums.add(getRandomAlbum());
		}
		return albums;
	}
	
	public Set<Album> getRandomUniqueSetOfAlbums(Set<Album> albums) {
		return getUnique(albums, () -> getRandomSetOfAlbums());
	}

	public Set<Artist> getRandomSetOfArtists(){
		Set<Artist> artists = Sets.newLinkedHashSet();
		Random random = new Random();
		for(int i=0; i<random.nextInt(19)+1; i++){
			artists.add(getRandomArtist());
		}
		return artists;
	}
	
	public Set<Artist> getRandomUniqueSetOfArtists(Set<Artist> artists) {
		return getUnique(artists, () -> getRandomSetOfArtists());
	}

	public Artist getRandomArtist(){
		Random random = new Random();
		final TypeArtist type = TypeArtist.GROUP;
		Artist artist = factories.getArtistFactory()
				.createArtist(type, "art"+random.nextInt(RANDOM_LENGTH));
		return artist;
	}
	
	public Artist getRandomUniqueArtist(Artist artist) {
		return getUnique(artist, () -> getRandomArtist());
	}

	public Set<Genre> getRandomSetOfGenres(){
		Set<Genre> genres = Sets.newLinkedHashSet();
		Random random = new Random();
		for(int i=0; i<random.nextInt(19)+1; i++){
			genres.add(getRandomGenre());
		}
		return genres;
	}
	
	public Set<Genre> getRandomUniqueSetOfGenres(Set<Genre> genres) {
		return getUnique(genres, () -> getRandomSetOfGenres());
	}

	public Genre getRandomGenre(){
		Random random = new Random();
		return factories.getGenreFactory().createGenre("Genre"+random.nextInt(RANDOM_LENGTH));
	}
	
	public Genre getRandomUniqueGenre(Genre genre) {
		return getUnique(genre, () -> getRandomGenre());
	}
	
	public TypeTrack getRandomTrackType() {
		final TypeTrack[] values = TypeTrack.values();
		final Random random = new Random();
		final int index = random.nextInt(values.length);
		return values[index];
	}
	
	public TypeTrack getRandomUniqueTrackType(TypeTrack type) {
		return getUnique(type, () -> getRandomTrackType());
	}

	public TypeVersion getRandomVersionType() {
		final TypeVersion[] values = TypeVersion.values();
		final Random random = new Random();
		final int index = random.nextInt(values.length);
		return values[index];
	}
	
	public TypeVersion getRandomUniqueVersionType(TypeVersion version) {
		return getUnique(version, () -> getRandomVersionType());
	}
	
	public Map<String, String> getRandomDataMap(int size){
		Map<String, String> randomData = new HashMap<>();

		for(int i=0; i<size; i++){
			randomData.put(randomString(), randomString());
		}
		return randomData;
	}

	public Playlist getRandomPlaylist() {
		return factories.getPlaylistFactory()
				.createStaticPlaylist(randomString());
	}
	
	public Playlist getRandomUniquePlaylist(Playlist playlist) {
		return getUnique(playlist, () -> getRandomPlaylist());
	}

	public List<String> getRandomDataList(int size){
		List<String> randomData = new ArrayList<>();

		for(int i=0; i<size; i++){
			randomData.add(randomString());
		}

		return randomData;
	}

	public String randomString() {
		return randomStringGenerator.generate(20);
	}
	
}
