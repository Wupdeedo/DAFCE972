import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;


public class DatabaseHandle {

	private Connection con;

	private PreparedStatement vbc;
	private PreparedStatement fa;
	private PreparedStatement sf;
	private PreparedStatement gac;
	private PreparedStatement gan;
	private PreparedStatement bookFlightChair;
	private PreparedStatement createBooking;
	private PreparedStatement getBookingID;
	private PreparedStatement createAccount;
	private PreparedStatement getAccountID;
	private PreparedStatement getAvailableCars;
	private PreparedStatement bookCar;
	private PreparedStatement getDepots;
	private PreparedStatement getAvailableHotels;
	private PreparedStatement getRooms;
	private PreparedStatement bookRoom;
	private PreparedStatement getEventsByLocation;
	private PreparedStatement getTotalSpots;
	private PreparedStatement bookEvent;
	private PreparedStatement logIn;
	private PreparedStatement searchWithFilters;
	private PreparedStatement getCountryByLocation;
	private PreparedStatement getRoomCount;
	private PreparedStatement getBookingsByAccount;
	private PreparedStatement getTotalChairs;

	public DatabaseHandle(){
		try
		{
			Class.forName ("org.postgresql.Driver");
			Properties settings = new Properties ();
			settings.setProperty ("user", LoginData.USER);
			settings.setProperty("password",LoginData.PASSWORD);
			con = DriverManager.getConnection (LoginData.URL, settings);
			//Stöder full sökning av vaccinationsinformation samt landsinfo baserat på landsnamn.
			vbc = con.prepareStatement("SELECT Vaccination.VaccBeskrivning, Landsinfo FROM Vaccination NATURAL INNER JOIN VacciLand NATURAL INNER JOIN Landsinfo WHERE Landsinfo.Land = ?");
			fa = con.prepareStatement("SELECT Avgangsdatum, MIN(Kostnad) AS Pris FROM Flight NATURAL INNER JOIN Stol NATURAL INNER JOIN Ort WHERE Avgangsort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Ankomstort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Avgangsdatum BETWEEN ? AND ? GROUP BY Avgangsdatum;");
			//Har stöd för Sökning av flyg enligt bollen. man söker på Utresedatum, Avgangsort och ankomstort. För att söka hemresa byter man plats på dessa fält.
			sf = con.prepareStatement("SELECT FlightID, Avgangstid, Ankomsttid, Typ, MIN(Kostnad) AS MinKostnad FROM Flight NATURAL INNER JOIN Flygplan NATURAL INNER JOIN Flygplanstyp NATURAL INNER JOIN Stol WHERE Avgangsdatum = ? AND Avgangsort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Ankomstort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Platser > (SELECT COUNT(*) FROM Flightbokning WHERE Flightbokning.FlightID = Flight.FlightID) GROUP BY FlightID, Avgangstid, Ankomsttid, Typ");
			//Variant med filter. Har atm stöd för sållning på pris.
			searchWithFilters = con.prepareStatement("SELECT FlightID, Avgangstid, Ankomsttid, Typ, MIN(Kostnad) AS MinKostnad FROM Flight NATURAL INNER JOIN Flygplan NATURAL INNER JOIN Flygplanstyp NATURAL INNER JOIN Stol WHERE Avgangsdatum = ? AND Avgangsort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Ankomstort = (SELECT OrtID FROM Ort WHERE Ort = ?) AND Platser > (SELECT COUNT(*) FROM Flightbokning WHERE Flightbokning.FlightID = Flight.FlightID) AND Kostnad > ? AND Kostnad < ? GROUP BY FlightID, Avgangstid, Ankomsttid, Typ");
			gac = con.prepareStatement("SELECT Typ, COUNT(*) AS Tillg FROM Stol NATURAL INNER JOIN Flight WHERE FlightID = ? AND NOT EXISTS (SELECT FlightID, Nummer FROM Flightbokning WHERE Flight.FlightID = Flightbokning.FlightID AND Stol.Nummer = Flightbokning.Nummer) GROUP BY Typ");
			gan = con.prepareStatement("SELECT Nummer FROM Stol NATURAL INNER JOIN Flight WHERE FlightID = ? AND Typ = ?::klass AND NOT EXISTS (SELECT * FROM Flightbokning WHERE Flightbokning.Nummer = Stol.Nummer)");
			createBooking = con.prepareStatement("INSERT INTO Bokning (KontoID) VALUES (?)");
			getBookingID = con.prepareStatement("SELECT MAX(BokningsID) AS Max FROM Bokning WHERE KontoID = ?");
			bookFlightChair = con.prepareStatement("INSERT INTO Flightbokning VALUES (?, ?, ?)");
			
			createAccount = con.prepareStatement("INSERT INTO Konto (Login, Password, Fornamn, Efternamn, Postort, Postnr, email, Adress, Telefon, TaBort) VALUES (?, decode(md5(?), 'hex'), ?, ?, ?, ?, ?, ?, ?, ?)");
			getBookingsByAccount = con.prepareStatement("SELECT BokningsID FROM Bokning WHERE KontoID = ?");
			getAccountID = con.prepareStatement("SELECT KontoID FROM Konto WHERE Login = ?");
			//Stöder sökning av bilar baserat på utlämningsort och de datum bilen skall bokas. Returnerar all relevant information för klassning.
			getAvailableCars = con.prepareStatement("SELECT BilID, Modell, Tillverkare, Drift, Vaxel, Lamningsplats AS DepotID FROM Bil NATURAL INNER JOIN Bilmodell NATURAL INNER JOIN Bilschema WHERE NOT EXISTS (SELECT * FROM Bilschema WHERE Bilschema.BilID = Bil.BilID AND ((? > Hamtdatum AND ? < Lamningsdatum) AND (? > Hamtdatum AND ? < Lamningsdatum) OR Hamtdatum BETWEEN ? AND ?)) AND (SELECT OrtID FROM Ort WHERE Ort = ?) = (SELECT OrtID FROM Bilschema  INNER JOIN Depot ON Bilschema.lamningsplats=Depot.DepotID WHERE Lamningsdatum = (SELECT MAX(Lamningsdatum) FROM Bilschema AS a WHERE a.BilID = Bilschema.BilID))");
			bookCar = con.prepareStatement("INSERT INTO Bilschema VALUES (?, ?, ?, ?, ?, ?)");
			getDepots = con.prepareStatement("SELECT DepotID FROM Depot NATURAL INNER JOIN Ort WHERE Ort = ?");
			
			//Hämtar alla hotell som har minst ett rum ledigt.
			getAvailableHotels = con.prepareStatement("SELECT HotellID, Namn, Ort, Stjarnor, Beskrivning, MIN(Kostnad) AS Minkostnad FROM Hotell NATURAL INNER JOIN Rum NATURAL INNER JOIN Ort WHERE Ort = ? AND EXISTS (SELECT RumsID FROM Rum AS r WHERE r.HotellID = Hotell.HotellID EXCEPT (SELECT RumsID FROM Rumsschema WHERE (? > Frandatum AND ? < Tilldatum) OR (? > Frandatum AND ? < Tilldatum) OR Frandatum BETWEEN ? AND ?)) GROUP BY HotellID, Namn, Ort, Stjarnor, Beskrivning");
			
			//Hämtar alla rum vid ett givet hotell som är lediga under den angivna tiden. Kan kombineras med föregående query för att få stöd för bokning av flera rum.
			getRooms = con.prepareStatement("SELECT RumsID FROM Rum WHERE HotellID = ? EXCEPT (SELECT RumsID FROM Rumsschema WHERE (? > Frandatum AND ? < Tilldatum) OR (? > Frandatum AND ? < Tilldatum) OR Frandatum BETWEEN ? AND ?)");
			getRoomCount = con.prepareStatement("SELECT (COUNT(RumsID)- (SELECT COUNT(RumsID) FROM Rumsschema WHERE (? > Frandatum AND ? < Tilldatum) OR (? > Frandatum AND ? < Tilldatum) OR Frandatum BETWEEN ? AND ?)) AS AntalRum FROM Rum WHERE HotellID = ?");
			bookRoom = con.prepareStatement("INSERT INTO Rumsschema VALUES (?,?,?,?)");
			//Stöder sökning av events baserat på ort samt en datumrange.
			getEventsByLocation = con.prepareStatement("SELECT EvenemangsID, Namn, Startdatum, Slutdatum, Starttid, Sluttid, Beskrivning, PlatsNamn, Kostnad FROM Evenemang NATURAL INNER JOIN Platser NATURAL INNER JOIN Ort WHERE Ort = ? AND Startdatum BETWEEN ? AND ? AND AntalPlatser > (SELECT COALESCE(SUM(Antal), 0) FROM Evenemangsschema WHERE Evenemang.EvenemangsID=Evenemangsschema.EvenemangsID)");
			getTotalSpots = con.prepareStatement("SELECT (Antalplatser::int - (SELECT COALESCE(SUM(Antal), 0)::int FROM Evenemangsschema WHERE EvenemangsID = ?)) AS Sum FROM Evenemang WHERE EvenemangsID = ?");
			bookEvent = con.prepareStatement("INSERT INTO Evenemangsschema VALUES (?, ?, ?)");
			logIn = con.prepareStatement("SELECT KontoID FROM Konto WHERE Login=? AND Password=decode(md5(?), 'hex');");
			getCountryByLocation = con.prepareStatement("SELECT LandsID, Land, Information FROM Landsinfo NATURAL INNER JOIN Ort WHERE Ort = ?");

			getTotalChairs = con.prepareStatement("SELECT Typ, COUNT(*) AS Antal FROM Stol NATURAL INNER JOIN Flight WHERE FlightID = ? GROUP BY Typ");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	//Validated
	public LinkedList<Integer> getBookingsByAccount(int kontoID) throws SQLException{
		getBookingsByAccount.setInt(1, kontoID);
		ResultSet rs = getBookingsByAccount.executeQuery();
		LinkedList<Integer> res = new LinkedList<Integer>();
		while(rs.next()){
			res.add(rs.getInt("BokningsID"));
		}
		return res;
	}

	public void close() throws SQLException{
		con.close();
	}

	//Validated
	public int getRoomCount(int hotellID, Date fran, Date till) throws SQLException{
		getRoomCount.setInt(7, hotellID);
		getRoomCount.setDate(1, new java.sql.Date(fran.getTime()));
		getRoomCount.setDate(2, new java.sql.Date(fran.getTime()));
		getRoomCount.setDate(3, new java.sql.Date(till.getTime()));
		getRoomCount.setDate(4, new java.sql.Date(till.getTime()));
		getRoomCount.setDate(5, new java.sql.Date(fran.getTime()));
		getRoomCount.setDate(6, new java.sql.Date(till.getTime()));
		ResultSet rs = getRoomCount.executeQuery();
		rs.next();
		return rs.getInt("AntalRum");

	}

	//Validated
	public ResultSet getCountryByLocation(String ort) throws SQLException{
		getCountryByLocation.setString(1, ort);
		return getCountryByLocation.executeQuery();

	}

	//Validated
	public long logIn(String user, String pass) throws SQLException{
		logIn.setString(1, user);
		logIn.setString(2, pass);
		ResultSet rs = logIn.executeQuery();
		if(!rs.next()) return -1;
		return rs.getLong("KontoID");
	}
	
	
	
	
	//Validated
	public void bookEvent(int evenemangsID, long bokningsID, int antal) throws SQLException{
		bookEvent.setInt(1, evenemangsID);
		bookEvent.setLong(2, bokningsID);
		bookEvent.setInt(3, antal);
		bookEvent.executeUpdate();

	}

	//Validated
	public int spotsLeft(int eventID) throws SQLException{
		getTotalSpots.setInt(1, eventID);
		getTotalSpots.setInt(2, eventID);
		ResultSet rs = getTotalSpots.executeQuery();
		rs.next();
		return rs.getInt("Sum");

	}

	//Validated
	public ResultSet getEventsByLocation(String ort, Date startdatum, Date slutdatum) throws SQLException{
		getEventsByLocation.setString(1, ort);
		getEventsByLocation.setDate(2, new java.sql.Date(startdatum.getTime()));
		getEventsByLocation.setDate(3, new java.sql.Date(slutdatum.getTime()));
		return getEventsByLocation.executeQuery();
	}

	//Validated
	public void bookRoom(long bokningsID, int hotellID, Date fran, Date till) throws SQLException{
		getRooms.setInt(1, hotellID);
		getRooms.setDate(2, new java.sql.Date(fran.getTime()));
		getRooms.setDate(3, new java.sql.Date(fran.getTime()));
		getRooms.setDate(4, new java.sql.Date(till.getTime()));
		getRooms.setDate(5, new java.sql.Date(till.getTime()));
		getRooms.setDate(6, new java.sql.Date(fran.getTime()));
		getRooms.setDate(7, new java.sql.Date(till.getTime()));
		ResultSet rs = getRooms.executeQuery();
		rs.next();
		bookRoom.setLong(1, bokningsID);
		bookRoom.setInt(2, rs.getInt("RumsID"));
		bookRoom.setDate(3, new java.sql.Date(fran.getTime()));
		bookRoom.setDate(4, new java.sql.Date(till.getTime()));
		bookRoom.executeUpdate();
	}

	//Validated
	public ResultSet getAvailableHotels(String ort, Date fran, Date till) throws SQLException{
		getAvailableHotels.setString(1, ort);
		getAvailableHotels.setDate(2, new java.sql.Date(fran.getTime()));
		getAvailableHotels.setDate(3, new java.sql.Date(fran.getTime()));
		getAvailableHotels.setDate(4, new java.sql.Date(till.getTime()));
		getAvailableHotels.setDate(5, new java.sql.Date(till.getTime()));
		getAvailableHotels.setDate(6, new java.sql.Date(fran.getTime()));
		getAvailableHotels.setDate(7, new java.sql.Date(till.getTime()));
		return getAvailableHotels.executeQuery();
	}

	//Validated
	public void bookCar(int bilID, long bokningsID, Date hamtdatum, Date lamningsdatum, int hamtplats, String lamningsort) throws SQLException{
		getDepots.setString(1, lamningsort);
		ResultSet rs = getDepots.executeQuery();
		rs.next();
		int depotID = rs.getInt("DepotID");
		bookCar.setInt(1, bilID);
		bookCar.setLong(2, bokningsID);
		bookCar.setDate(3, new java.sql.Date(hamtdatum.getTime()));
		bookCar.setDate(4, new java.sql.Date(lamningsdatum.getTime()));
		bookCar.setInt(5, hamtplats);
		bookCar.setInt(6, depotID);
		bookCar.executeUpdate();

	}

	//Validated
	public ResultSet getAvailableCars(String ort, Date dat1, Date dat2) throws SQLException{
		getAvailableCars.setDate(1, new java.sql.Date(dat1.getTime()));
		getAvailableCars.setDate(2, new java.sql.Date(dat1.getTime()));
		getAvailableCars.setDate(3, new java.sql.Date(dat2.getTime()));
		getAvailableCars.setDate(4, new java.sql.Date(dat2.getTime()));
		getAvailableCars.setDate(5, new java.sql.Date(dat1.getTime()));
		getAvailableCars.setDate(6, new java.sql.Date(dat2.getTime()));
		getAvailableCars.setString(7, ort);

		return getAvailableCars.executeQuery();

	}


	//Validated
	public int createAccount(String user, String password, String fornamn, String efternamn, String postort, int postnr, String email, String adress, long telefon, Date tabort) throws SQLException{
		createAccount.setString(1, user);
		createAccount.setString(2, password);
		createAccount.setString(3, fornamn);
		createAccount.setString(4, efternamn);
		createAccount.setString(5, postort);
		createAccount.setInt(6, postnr);
		createAccount.setString(7, email);
		createAccount.setString(8, adress);
		createAccount.setLong(9, telefon);
		createAccount.setDate(10, new java.sql.Date(tabort.getTime()));
		createAccount.executeUpdate();

		getAccountID.setString(1, user);
		ResultSet rs = getAccountID.executeQuery();
		rs.next();
		return rs.getInt("KontoID");

	}

	//Validated
	public int createBooking(int kontoID) throws SQLException{
		createBooking.setInt(1, kontoID);
		createBooking.executeUpdate();
		getBookingID.setInt(1, kontoID);
		ResultSet rs = getBookingID.executeQuery();
		rs.next();
		return rs.getInt("Max");

	}

	//Validated
	public void bookChair(int nummer, int bokningsID, int flightID) throws SQLException{
		bookFlightChair.setInt(1, flightID);
		bookFlightChair.setInt(2, bokningsID);
		bookFlightChair.setInt(3, nummer);
		bookFlightChair.executeUpdate();

	}

	//Validated
	public LinkedList<Integer> getAvailableNumbers(int flightID, String type) throws SQLException{
		gan.setInt(1, flightID);
		gan.setString(2, type);
		ResultSet rs = gan.executeQuery();
		LinkedList<Integer> res = new LinkedList<Integer>();
		while(rs.next()){
			res.add(rs.getInt("Nummer"));
		}
		return res;
	}

	//Validated
	public HashMap<String, Integer> getAvailableChairs(int flightID) throws SQLException{
		gac.setInt(1, flightID);
		ResultSet rs = gac.executeQuery();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		while(rs.next()){
			res.put(rs.getString("Typ"), rs.getInt("Tillg"));
		}
		return res;
	}
	
	//Validated
	public HashMap<String, Integer> getTotalChairs(int flightID) throws SQLException{
		getTotalChairs.setInt(1, flightID);
		ResultSet rs = getTotalChairs.executeQuery();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		while(rs.next()){
			res.put(rs.getString("Typ"), rs.getInt("Antal"));
		}
		return res;
	}

	//Validated
	public ResultSet searchFlight(Date avgangsdatumRaw, String avgangsort, String ankomstort) throws SQLException{
		java.sql.Date avgangsdatum = new java.sql.Date(avgangsdatumRaw.getTime());
		sf.setDate(1, avgangsdatum);
		sf.setString(2, avgangsort);
		sf.setString(3, ankomstort);
		return sf.executeQuery();

	}
	
	//Validated
	public ResultSet searchFlightFilters(Date avgangsdatumRaw, String avgangsort, String ankomstort, int minpris, int maxpris) throws SQLException{
		java.sql.Date avgangsdatum = new java.sql.Date(avgangsdatumRaw.getTime());
		searchWithFilters.setDate(1, avgangsdatum);
		searchWithFilters.setString(2, avgangsort);
		searchWithFilters.setString(3, ankomstort);
		searchWithFilters.setInt(4, minpris);
		searchWithFilters.setInt(5, maxpris);

		return searchWithFilters.executeQuery();

	}

	//Validated
	public LinkedList<DatePrice> fillAlmanack(Date date, String avgang, String ankomst) throws SQLException{

		fa.setString(1, avgang);
		fa.setString(2, ankomst);

		//17 days before and after
		fa.setDate(3, new java.sql.Date(date.getTime()-1468800000));
		fa.setDate(4, new java.sql.Date(date.getTime()+1468800000));
		ResultSet rs = fa.executeQuery();
		LinkedList<DatePrice> list = new LinkedList<DatePrice>();
		while(rs.next()){
			list.add(new DatePrice(rs.getDate("Avgangsdatum"), rs.getInt("Pris")));
		}
		return list;

	}

	//Validated
	public LinkedList<String> getVaccinationByCountry(String country) throws SQLException{
		vbc.setString(1, country);
		ResultSet rs = vbc.executeQuery();
		LinkedList<String> ret = new LinkedList<String>();
		while(rs.next()){
			ret.add(rs.getString("VaccBeskrivning"));
		}
		return ret;
	}



}

