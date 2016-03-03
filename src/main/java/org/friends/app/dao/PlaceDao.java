package org.friends.app.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.friends.app.model.Place;

import spark.utils.Assert;

public class PlaceDao {

	private static List<Place> placeCache = new ArrayList<Place>();
	static String strDateToday  = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    
    static{
    	placeCache.add(new Place(1, true, strDateToday));//Place libre aujourd'hui free = true
    	placeCache.add(new Place(2, false, strDateToday));//Place occupée aujourd'hui
    	placeCache.add(new Place(3, "damien.urvoix", strDateToday));//place occupée aujourd'hui
    	placeCache.add(new Place(4, null, strDateToday)); // place libre  aujourd'hui
    	placeCache.add(new Place(33, true, strDateToday)); //Place libre aujourd'hui free = true
    	placeCache.add(new Place(34, true, "01/01/2015")); //Place libre hier free = true
    	placeCache.add(new Place(35, true, "01/01/2017")); //Place libre dans le futur free = true
    	placeCache.add(new Place(36, null, "01/01/2015")); //Place libre hier occupee == null
    	placeCache.add(new Place(37, null, "01/01/2017")); //Place libre dans le futur occupee == null
    }		
	
	public Place persist(Place place) {
		Assert.notNull(place);
		placeCache.add(place);
		return place;
	}

	public List<Integer> findAllFree() {
		List<Integer> listFree = new ArrayList<Integer>();
		for (Iterator<Place> iterator = placeCache.iterator(); iterator.hasNext();) {
			Place place = (Place) iterator.next();
			if(place.isFree())	listFree.add(place.getPlaceNumber());
		}
		
		return listFree;
	}

	public List<Integer> findAllFree(Date date) throws ParseException {
		List<Integer> listFree = new ArrayList<Integer>();
		for (Iterator<Place> iterator = placeCache.iterator(); iterator.hasNext();) {
			Place place = (Place) iterator.next();
			if(place.isFree() && (strDateToday.equalsIgnoreCase(place.getOccupationDate())))	listFree.add(place.getPlaceNumber());
		}
		
		return listFree;
	}
}