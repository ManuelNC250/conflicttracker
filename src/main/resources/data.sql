-- Versión mejorada con validaciones
-- Elimina datos existentes primero (solo para desarrollo)
DELETE FROM conflict_countries;
DELETE FROM faction_supporting_countries;
DELETE FROM events;
DELETE FROM factions;
DELETE FROM conflicts;
DELETE FROM countries;

-- Reinicia secuencias (PostgreSQL)
ALTER SEQUENCE IF EXISTS countries_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS conflicts_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS factions_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS events_id_seq RESTART WITH 1;

-- Países
INSERT INTO countries (id, name, code) VALUES
                                           (1, 'Ukraine', 'UKR'),
                                           (2, 'Russia', 'RUS'),
                                           (3, 'Israel', 'ISR'),
                                           (4, 'Palestine', 'PSE'),
                                           (5, 'Syria', 'SYR'),
                                           (6, 'Yemen', 'YEM');

-- Conflictos
INSERT INTO conflicts (id, name, start_date, status, description) VALUES
                                                                      (1, 'War in Ukraine', '2022-02-24', 'ACTIVE', 'Military conflict between Russia and Ukraine'),
                                                                      (2, 'Israel-Palestine Conflict', '2023-10-07', 'ACTIVE', 'Ongoing conflict between Israel and Hamas'),
                                                                      (3, 'Syrian Civil War', '2011-03-15', 'FROZEN', 'Multi-sided civil war in Syria');

-- Relaciones Conflictos-Países
INSERT INTO conflict_countries (conflict_id, country_id) VALUES
                                                             (1, 1), (1, 2),
                                                             (2, 3), (2, 4),
                                                             (3, 5);

-- Facciones
INSERT INTO factions (id, name, conflict_id) VALUES
                                                 (1, 'Ukrainian Armed Forces', 1),
                                                 (2, 'Russian Armed Forces', 1),
                                                 (3, 'Israel Defense Forces', 2),
                                                 (4, 'Hamas', 2),
                                                 (5, 'Syrian Government', 3),
                                                 (6, 'Free Syrian Army', 3);

-- Eventos
INSERT INTO events (id, event_date, location, description, conflict_id) VALUES
                                                                            (1, '2022-02-24', 'Kyiv', 'Beginning of full-scale invasion', 1),
                                                                            (2, '2023-10-07', 'Gaza Strip', 'Hamas attack on Israel', 2),
                                                                            (3, '2011-03-15', 'Daraa', 'Start of Syrian uprising', 3);