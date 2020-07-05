CREATE TABLE gap.prenotazioni (
  utente VARCHAR(45) NOT NULL,
  aula VARCHAR(45) NOT NULL,
  giorno DATE NOT NULL,
  orainizio INT NOT NULL,
  PRIMARY KEY (utente, aula, giorno, orainizio));