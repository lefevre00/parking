package org.friends.app.view.route;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.friends.app.dao.PlaceDao;
import org.friends.app.model.Place;
import org.friends.app.service.impl.PlaceServiceBean;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SearchRoute extends AuthenticatedRoute {
	
	static DateTimeFormatter formatterDatePicker = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy");

	private PlaceServiceBean placeService = new PlaceServiceBean();
	
	@Override
	public ModelAndView doHandle(Request req, Response resp) {
    	Map<String, Object> map = new HashMap<>();
    	
    	LocalDate now = LocalDate.now();
    	String paramDate = req.queryParams("day");
		String dateRecherchee = paramDate != null ? paramDate : rechercheLaProchaineDateUtilisable();
		LocalDate dateRechercheeAsDate = LocalDate.parse(dateRecherchee, PlaceDao.formatter);

    	// Previous date
    	String previous = null;
		if (paramDate != null) {
    		previous = rechercherLejourPrecedent(dateRechercheeAsDate);
    		map.put("previous", previous);
    	}
    	
    	// Next date
    	LocalDate nextDate  = dateRecherchee != null ? dateRechercheeAsDate : now;
    	String next = rechercherLejourSuivant(nextDate);
    	map.put("next", next);
    	
    	map.put("dateRecherche", dateRechercheeAsDate.format(formatterDatePicker));
    	map.put("dateBook", dateRecherchee);
    	List<Place> places = new ArrayList<Place>();
		try {
			places = getPlaces(dateRechercheeAsDate);
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (!places.isEmpty()){
    		map.put("place", places.get(0));
    	}
        return new ModelAndView(map, "search.ftl");
	}

	

	private List<Place> getPlaces(LocalDate dateRecherche) throws SQLException, URISyntaxException {
		return placeService.getAvailableByDate(dateRecherche);
	}
}
