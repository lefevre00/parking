package org.friends.app.dao;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.friends.app.HibernateUtil;
import org.friends.app.model.Place;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import spark.utils.Assert;

public class PlaceDao {

	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PlaceDao.DATE_PATTERN);
	Session sessionHibernate = HibernateUtil.getSession();
	
	private final static Session session =  HibernateUtil.getSession();
	
	public Place persist(Place place) {
		session.beginTransaction();
		Serializable id = session.save( place );
		session.getTransaction().commit();
		return (Place) session.get(Place.class, id);
	}

	@SuppressWarnings("unchecked")
	public boolean userAsDejaReserveUnePlaceAcetteDate(LocalDate dateAsDate, String mailUser) {
		Assert.notNull(dateAsDate);
		Assert.notNull(mailUser);
		String strDateRecherche = dateAsDate.format(formatter);
		List<Place> listFree = new ArrayList<Place>();
		listFree = session.getNamedQuery(Place.QUERY_USER_HAS_BOOK_AT_THE_DATE)
				.setString("date", strDateRecherche)
				.setString("mailOccupant", mailUser)
				.list();
		return listFree.size() > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public List<Place> findAllBookedPlaceByUser(String emailAMDM) {
		Assert.notNull(emailAMDM);
		return session.getNamedQuery(Place.QUERY_ALL_PLACE_BY_USER)
				.setString("mailOccupant", emailAMDM)
				.setString("date", LocalDateTime.now().getHour()>Place.HEURE_CHANGEMENT_JOUR_RECHERCHE ? LocalDate.now().plusDays(1).format(PlaceDao.formatter) : LocalDate.now().format(PlaceDao.formatter))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Place> findReleaseHistoryByPlace(Integer placeNumber) {
		Assert.notNull(placeNumber);
		return session.getNamedQuery(Place.QUERY_RELEASE_HISTORY_BY_PLACE)
				.setInteger("place_number", placeNumber)
				.list();
	}
	
	public void clearAllPlacesBeforeDate(LocalDate date) {
		String strDateRecherche = date.format(formatter);
		List<Place> listeDesPlacesAsupprimer = findPlacesByCriterions(Restrictions.le("occupationDate", strDateRecherche));
		for (Iterator<Place> iterator = listeDesPlacesAsupprimer.iterator(); iterator.hasNext();) {
			Place place = iterator.next();
			session.delete(place);	
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Place> findPlacesByCriterions(Criterion ... criterions) {
		Assert.notNull(criterions);
		Assert.notEmpty(criterions, "restrictions must not be empty");
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(Place.class);
		for (int i = 0; i < criterions.length; i++) {
			criteria.add(criterions[i]);
		}
		return (List<Place>) criteria.list();
	}
}

