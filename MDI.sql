
CREATE TABLE Konto(
	KontoID bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	Login Varchar(40) UNIQUE,
	Password Binary(32),
	Fornamn Varchar(16),
	Efternamn Varchar(32),
	Postort Varchar(32),
	Postnr int,
	email Varchar(40), 
	Adress Varchar(60),
	Telefon bigint,
	TaBort Date,
	PRIMARY KEY (KontoID)
);


CREATE TABLE Bokning(
	BokningsID bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	KontoID bigint UNSIGNED NOT NULL,
	PRIMARY KEY (BokningsID, KontoID),
	FOREIGN KEY (KontoID) REFERENCES Konto(KontoID)
);



CREATE TABLE Rumsschema(
	BokningsID bigint UNSIGNED NOT NULL,
	RumsID int UNSIGNED NOT NULL,
	Frandatum date NOT NULL,
	Tilldatum date NOT NULL,
	PRIMARY KEY (RumsID,Frandatum,Tilldatum),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (HotellID) REFERENCES Rum(HotellID),
	FOREIGN KEY (Rumsnummer) REFERENCES Rum(Rumsnummer)
);

CREATE TABLE Evenemangsschema(
	EvenemangsID int UNSIGNED NOT NULL,
	BokningsID bigint UNSIGNED NOT NULL,
	Antal tinyint UNSIGNED,
	PRIMARY KEY (EvenemangsID, BokningsID),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (EvenemangsID) REFERENCES Evenemang(EvenemangsID)
);



CREATE TABLE Bilschema(
	BilID int UNSIGNED NOT NULL,
	BokningsID bigint UNSIGNED NOT NULL,
	Hamtdatum date NOT NULL,
	Lamningsdatum date NOT NULL,
	Hamtplats int UNSIGNED NOT NULL,
	Lamningsplats int UNSIGNED NOT NULL,
	PRIMARY KEY (BilID, Hamtdatum, Lamningsdatum),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (BilID) REFERENCES Bil(BilID),
	FOREIGN KEY (Hamtplats) REFERENCES Depot(DepotID),
	FOREIGN KEY (Lamningsplats) REFERENCES Depot(DepotID)
);

CREATE TABLE Flygplansbokning(
	FlightID int UNSIGNED NOT NULL,
	BokningsID bigint UNSIGNED NOT NULL,
	Nummer smallint UNSIGNED NOT NULL,
	PRIMARY KEY (FlightID, BokningsID, Nummer),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (FlightID) REFERENCES Flygning(FlightID)
);











